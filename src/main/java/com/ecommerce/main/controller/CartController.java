package com.ecommerce.main.controller;
import com.ecommerce.main.dto.CartDto;
import com.ecommerce.main.model.Cart;
import com.ecommerce.main.reposnse.ApiResponse;
import com.ecommerce.main.service.cart.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.prefix}/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServiceImpl cartService;
    private final ModelMapper modelMapper;


    @GetMapping("/getById/{cartId}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long cartId) {
       Cart cart= cartService.getCart(cartId);
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        return ResponseEntity.ok().body(new ApiResponse("sucess", cartDto));
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok().body(new ApiResponse("sucess", null));
    }

    @GetMapping("/clear/{cartId}")
    public ResponseEntity<ApiResponse> clearId(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok().body(new ApiResponse("sucess", null));
    }


    @PostMapping("/addItem/{productId}/{quantity}")
    public ResponseEntity<ApiResponse> post(@PathVariable Long productId, @PathVariable Integer quantity,@RequestParam(required = false) Long cartId) {
        Cart cart=cartService.addItemToCart(cartId, productId, quantity);
        CartDto cartDto = modelMapper.map(cart, CartDto.class);

        return ResponseEntity.ok().body(new ApiResponse("sucess",cartDto));
    }

    @DeleteMapping("/removeItems/{cartId}/{productId}")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.removeItemFromCart(cartId, productId);
        return ResponseEntity.ok().body(new ApiResponse("sucess", null));
    }
}
