import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {ButtonsModule, CollapseModule} from 'ngx-bootstrap';
import {MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

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
    ButtonsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule
  ],
  providers: [PostService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
