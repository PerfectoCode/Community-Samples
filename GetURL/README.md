# Using getCurrentUrl to retrieve WebView URL #
This sample script uses the Perfecto MultiView application to display two browser windows and then retrieve the URL displayed in each WebView. The main *trick* to keep in mind is to:
- Instrument the application during installation
- Change context to the proper WEBVIEW
