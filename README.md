# NEWS FEED APP

The app feeds news articles from https://newsapi.org/ and displays the result in a list or articles.

## APP Features

* The app is developed in kotlin
* The app supports screen rotation
* The size of the list is limited to 10 articles due to licencing restrictions
* The full article text is also limited to 260 characters and clipped upon reaching that limit
* The app has two flavors:
    * BBC News
    * CNN News
* The app changes it's listing layout from linear to grid when the screen is rotated and the screen widht is calculated as EXTENDED
* If available the app will request for a biometric input on it's start
* The app uses:
    * Hilt for dependency Injection
    * Picasso for image downloading and caching
    * Retrofit for HTTP requests
    * GSON for mapping results into objects
    * JUnit, Espresso and Roboelectric for testing
    * AndroidX Window for adaptative layout