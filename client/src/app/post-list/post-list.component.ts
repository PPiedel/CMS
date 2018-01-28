import {Component, OnInit} from '@angular/core';
import {PostService} from '../shared/posts/post.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {
  posts: Array<any>;

  constructor(private postService: PostService) {
  }

  ngOnInit() {
    this.postService.getAllPosts().subscribe(data => {
      this.posts = data;
    });
  }

}
