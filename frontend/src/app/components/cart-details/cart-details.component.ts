import { Component, OnInit } from '@angular/core';
import { CartItem } from 'src/app/common/cart-item';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {
remove(theCartItem: CartItem) {
this.cartService.remove(theCartItem);
}
decrementQuantity(theCartItem: CartItem) {
this.cartService.decrementQuantity(theCartItem);
}
incrementQuantity(theCartItem: CartItem) {

  this.cartService.addToCart(theCartItem);
}

  cartItems:CartItem[]=[];
  totalPrice:number=0;
  totalQuantity:number=0;
  
  constructor(private cartService:CartService) { }

  ngOnInit(): void {
    this.listCartDetails();
  }
  listCartDetails() {
    this.cartItems=this.cartService.cartItems;
    this.cartService.totalPrice.subscribe(data=>this.totalPrice=data);
    this.cartService.totalQuantity.subscribe(data=>this.totalQuantity=data);
    this.cartService.computeCartTotals();
  }

}
