package com.ecommerce.main.service.cart;
import com.ecommerce.main.exception.errors.ProductNotFound;
import com.ecommerce.main.model.Cart;
import com.ecommerce.main.model.CartItem;
import com.ecommerce.main.model.Product;
import com.ecommerce.main.repository.CartItemRepository;
import com.ecommerce.main.repository.CartRepository;
import com.ecommerce.main.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
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
        cartRepository.save(find);

    }

    @Override
    public BigDecimal getTotalPrice(Long  cartId) {
        Cart find=getCart(cartId);

        return find.getCartItems().stream().map(item->item.getUnitPrice().multiply(new BigDecimal(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItemToCart(Long cartId, Long productId, int quantity) {
        Cart find = getCart(cartId);
        CartItem cartItems=find.getCartItems().stream().filter(item->item.getProduct().getId().equals(productId)).findFirst().orElse(null);
        if(cartItems==null){
            CartItem cartItem=new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setCart(find);
            Product product=productRepository.findById(productId).orElseThrow(()->new ProductNotFound("PRODUCT NOT FOUND !"));
            cartItem.setProduct(product);
            cartItem.setUnitPrice(new BigDecimal(String.valueOf(product.getPrice())));
            find.getCartItems().add(cartItem);
        }
        else {
            cartItems.setQuantity(quantity);
        }
        find.setTotalPrice(getTotalPrice(find.getId()));
        cartRepository.save(find);
    }

    public void removeItemFromCart(Long cartId, Long productId) {
        Cart find = getCart(cartId);
        CartItem cartItem=find.getCartItems().stream().filter(item->item.getProduct().getId().equals(productId)).findFirst().orElseThrow(()->new ProductNotFound("this card diesnont have this item !"));
        find.getCartItems().remove(cartItem);
        find.setTotalPrice(getTotalPrice(find.getId()));
        cartRepository.save(find);
    }
}
