# CleanCodeArchitecture 

The application comprises two screens designed with the following specifications. The initial screen showcases a scrollable list of dog breeds fetched from the Dogs API (https://dog.ceo/dog-api/). Tapping on a specific breed in the list navigates the user to the second screen.

On the second screen, the app loads 10 random images of the selected breed. The development follows the MVVM architecture, employing Android Jetpack components to enhance project lifecycle awareness. Clean code architecture principles are adhered to, with comprehensive test cases covering the data layer, interactors layer, framework layer, and presentation layer. Additionally, the project incorporates ViewModel testing, as well as testing for fragments and navigation.


Key technologies used in the CcaApp include:

Kotlin (with a focus on clean code architecture)

MVVM architecture

ConstraintLayout

ViewModel data binding

Navigation Graph

Koin Dependency Injection

jUnit, mock, espresso for unit testing

RxJava with executor and scheduler

Retrofit

Glide for image loading
