import { Component } from '@angular/core';
     
@Component({
    selector: 'my-app',
    templateUrl: './app.component.html'
})
export class AppComponent { 
    name= '';
    num = 7;

    doSomething() {
        console.log(this.num);
    }

    inkr(){
        this.num++;
        console.log(this.num)
    }

    dekr(){
        this.num--;
        console.log(this.num)
    }
}