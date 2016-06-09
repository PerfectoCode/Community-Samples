# Using getCurrentUrl #
This sample script uses the Perfecto MultiWebViews application to display two browser windows and then retrieve the URL displayed in each WebView. The main *tricks* to keep in mind are:
- Instrument the application during installation
- Change context to the proper WEBVIEW (using WEBVIEW-*n*)
