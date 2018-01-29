var AppComponent = ng.core.Component({
    selector: 'app',
    templateUrl: '../ng_templates/posts.html'
}).Class({
    constructor: [ng.http.Http, function (http) {
        var self = this;
        self.posts = "";
        http.get("/posts").subscribe(response => self.posts = response.json());
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