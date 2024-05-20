import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { KolcsonzesekComponent } from './kolcsonzesek/kolcsonzesek.component';
import { KolcsonzokComponent } from './kolcsonzok/kolcsonzok.component';

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "kolcsonzok", component: KolcsonzokComponent},
  {path: " ", component: HomeComponent},
  {path: "**", component: HomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
