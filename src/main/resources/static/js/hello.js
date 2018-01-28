var AppComponent = ng.core.Component({
    selector: 'app',
    template: '<div *ngFor="let post of posts">{{post.title}}</div>'
}).Class({
    constructor: [ng.http.Http, function (http) {
        var self = this;
        self.posts = "";
        http.get("/posts").subscribe(response = > self.posts = response.json()
    )
        ;
        console.log(self.posts.toString());
    }]
});

var AppModule = ng.core.NgModule({
    imports: [ng.platformBrowser.BrowserModule, ng.http.HttpModule],
    declarations: [AppComponent],
    bootstrap: [AppComponent]
}).Class(
    {
        constructor: function () {
        }
    });

document.addEventListener('DOMContentLoaded', function () {
    ng.platformBrowserDynamic.platformBrowserDynamic().bootstrapModule(AppModule);
});