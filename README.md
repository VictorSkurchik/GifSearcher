# GifSearcher
Simple android app that is able to show trending GIFs, search for GIFs and share GIFs with others

## Getting Started
Copy the repository `git clone https://github.com/VictorSkurchik/GifSearcher`

## Running the tests
Run `./gradlew check` (for Mac and Linux) or `gradlew check`(for Windows) to compile and run the unit tests. 

## Building and running
To build a debug version of your Android application, you can run `./gradlew assembleDebug` or `gradlew assembleDebug`(for Windows) from the root of your repository. In a default project setup, the resulting apk can then be found in `app/build/outputs/apk/app-debug.apk` On a Unix machine, you can also just run find . -name '*.apk' to find it, if it's not there.

## List of the libraries used in the project
* [AppCompat, CardView, RecyclerView an DesignLibrary](http://developer.android.com/intl/es/tools/support-library/index.html)
* [Data binding](https://erikcaffrey.github.io/ANDROID-databinding-android/)
* [Glide](https://bumptech.github.io/glide/)
* [Retrofit 2](http://square.github.io/retrofit/)
* [RxJava & RxAndroid](https://github.com/ReactiveX/RxAndroid)

## API Reference
The app requires a version of android API 17 and above.
In the project using [Giphi API](https://github.com/Giphy/GiphyAPI)

## Authors

* **Victor Skurchik** - <victorskurchik@gmail.com>
