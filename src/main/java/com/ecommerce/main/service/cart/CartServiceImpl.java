package com.ecommerce.main.service.cart;
import com.ecommerce.main.exception.errors.ProductNotFound;
import com.ecommerce.main.model.Cart;
import com.ecommerce.main.model.CartItem;
import com.ecommerce.main.model.Product;
import com.ecommerce.main.repository.CartItemRepository;
import com.ecommerce.main.repository.CartRepository;
import com.ecommerce.main.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;


    @Override
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new ProductNotFound("CART NOT FOUND !"));
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepository.findById(cartId).ifPresentOrElse(item->cartRepository.deleteById(cartId),()->{
            throw new ProductNotFound("CART NOT FOUND !");
        });

    }

    @Override
    public void clearCart(Long cartId) {
        Cart find=getCart(cartId);
        find.getCartItems().clear();
        BigDecimal total=getTotalPrice(find.getId());
        find.setTotalPrice(total);
        cartRepository.save(find);

    }

    @Override
    public BigDecimal getTotalPrice(Long  cartId) {
        Cart cart= cartRepository.findById(cartId).orElseThrow(()->new ProductNotFound("not found"));

        return cart.getCartItems().stream().map(item->item.getUnitPrice().multiply(new BigDecimal(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    @Transactional
    public Cart addItemToCart(Long cartId, Long productId, int quantity) {

        Cart find;
        if(cartId==null){
            Cart newCart = new Cart();
            newCart.setTotalPrice(BigDecimal.ZERO);
            find = cartRepository.save(newCart);

        }
        else{
            find=getCart(cartId);
        }

        CartItem existingItem=find.getCartItems().stream().filter(item->item.getProduct().getId().equals(productId)).findFirst().orElse(null);
        if(existingItem==null){
            CartItem cartItem=new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setCart(find);
            Product product=productRepository.findById(productId).orElseThrow(()->new ProductNotFound("PRODUCT NOT FOUND !"));
            cartItem.setProduct(product);
            cartItem.setUnitPrice(product.getPrice());
            cartItem.setTotalPrice();

            find.getCartItems().add(cartItem);


        }
        else {
            existingItem.setQuantity(quantity);
            existingItem.setTotalPrice();
        }

        find.setTotalPrice(getTotalPrice(find.getId()));
        return cartRepository.save(find);

    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()->new ProductNotFound("CART NOT FOUND !"));
        CartItem cartItem=cart.getCartItems().stream().filter(item -> item.getProduct().getId().equals(productId)).findFirst().orElseThrow(() -> new ProductNotFound("not found "));
        cart.getCartItems().remove(cartItem);
        BigDecimal total=getTotalPrice(cartId);
        cart.setTotalPrice(total);
        cartRepository.save(cart);


    }
}
