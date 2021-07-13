package com.tunnelnetwork.KpOnlineStore.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController extends CommonController{

  @GetMapping("/cart/home")
  private String cartHomePage(Model model) {
    if (!isThereLoggedInUser() || cartRepository.getCartOfUser() == null) {
      return "redirect:/";
    }

    model.addAttribute("cart", cartRepository.getCartOfUser());

    return "cart";
  }

  // Shopping cart buttons logic
  @RequestMapping(value = "/addProduct", method=RequestMethod.POST)
  @ResponseBody
  private ModelAndView addProduct(@RequestParam("productId") long id, @RequestParam("productQuantity") Integer productQuantity) {
    if (!cartRepository.isProductInCart(id)) {
      cartRepository.addToCart(productRepository.getProduct(id), productQuantity);
    }

    return new ModelAndView("redirect:/store/1");
  }
  @RequestMapping(value="/removeProduct", method=RequestMethod.POST)
  @ResponseBody
  private ModelAndView removeProduct(@RequestParam("productId") long id){
    if (cartRepository.isProductInCart(id)) {
      cartRepository.removeProduct(id);
    }

    return new ModelAndView("redirect:/cart/home");
  }
}
