import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {ButtonsModule, CollapseModule} from 'ngx-bootstrap';

import {AppComponent} from './app.component';
import {PostListComponent} from './post-list/post-list.component';

import {PostService} from './shared/posts/post.service';
import {NavbarComponent} from './shared/navbar/navbar.component';


@NgModule({
  declarations: [
    AppComponent,
    PostListComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CollapseModule,
    ButtonsModule
  ],
  providers: [PostService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
