# CashKaro
Shell app developed for cashkaro.com


Concepts used: Facebook API,Activity, Fragment, Fragment page adapter, Service, Broadcast receiver,Content provider, Sqlite database,
Loaders, Async Task, JSON data parsing, Alert dialog, Notification, Share action intent, Permission manager, WebView, Carousel View, Recyler View,Firebase integration,etc.


Project Overview:

Package:
com.silvermoon.carouselview

Contains all the relevant classes and interfaces for the implementation of Carousel View in Main activity.

Package:
com.silvermoon.data

Classes:
StoreDeals - Data model class used to capture data retrieved from the sqlite database
DBhelper - SQLiteDatabase class. Contains query for creation of tables and insertion of default values inside them.
StoreProvider - Content provider class. Contains the list of Uri. Provides all the necessary logic to query and update the tables 
present in underlying db.
DatabaseContract - Contains all the tables,list of authorities and columns. 
DealsUpdateService - Intent service which is used to perform database update operation in a background service. Sends a custom broadcast
after the deal has been updated.

Package:
com.silvermoon.menubaritems

Classes:
FBLoginActivty - Contains code related to Facebook SDK. Helps the user to login or logut using Facebook. JSON data parsing is used to display the username.
AccountItemsFragment - Displays dummy account items after user has successfully logged in.


Package:
com.silvermoon.cashkaro

Classes:
Constants - Cotains list of Uris and Store urls.
MainActivity - Mainscreen of the app. Conatins code for displaying navigation drawer, action bar, Camera and location permission, tabbed layout,etc.
MainScreenTabAdapter- Adapter class for tabs present in main screen.
MainScreenDealsFragment - Fragment for displaying items in Electronics tab
MainScreenDealsFragmentTwo - Fragment for displaying items in Movies tab
MainScreenDealsFragmentThree - Fragment for displaying items in Books tab


Package:
com.silvermoon.storepage

Classes:
StorePageActivity - Contains a recycler view to display list of deals in CardView, fetched from
underlying Sqlite databse connection.Contains appropriate code for handling button and Image Button clicks. Implements loader callbacks in order to make
databse querying opeartions lifecycle and orientation aware. Contains an internal class as a Broadcast Receiver. The Broadcast receiver receives a custom
broadcast from DealsUpdateService class, and restarts the loader in order to refresh the data in the recycler view.
StorePageAdapter - Adapter class for recycler view present in StorePageActivity.
WebViewActivity - Displays an url in WebView


Functionalities of App:

1. Main Screen - After the app opens, the main screen will display a carousel view, action bar, and three tabs with deals.
2. open the navigation drawer from left side. If you click on Camera or Location permission, it will prompt the use to allow or deny the permission.
3. Click on the user icon on the action bar to open the facebook login screen. It will provide options to login or logout using facebook.
4. In main screen, swipe or click on any tab (viz. Electronics, Movies, Books) in order to see the appropriate deals in card view.
5. In main screen, if you click on any item in carousel view it will take you to store page screen screen.
6. In store page screen, if you click on "Shop now" button it will load up the store's home page in webview
7. In store page screen, if you click on "GET CODE" button, it will display a notification, and provided internet connection is available it will display
an alert dialog which when accepted, will load the appropriate deal in webview.
8. Whenever you click on any deal's "GET CODE" button, firebase will trigger a SELECT_CONTENT event, and when you mark any deal as favourite firebase
will trigger ADD_TO_WISHLIST event.
9.All these recorded events can be viewed in Firebase console.

