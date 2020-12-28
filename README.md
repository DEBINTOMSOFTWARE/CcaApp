# CleanCodeArchitecture 

The App consists of two screens with below specifications
Specification
The first screen displays a list of dog breeds from the Dogs API (https://dog.ceo/dog-api/) and present the result in a scrolling list.
Tapping a breed from the list navigate to the second screen.
The second screen will then load with 10 random dog images of the selected breed.
The App runs successfully and works on below scenario's.
As a user running the application I can select breed from the list So that I can view pictures of that breed
Scenario 1: Viewing the breed list When I launch the app Then I see a list of dog breeds
Scenario 2: Viewing pictures of breed Given I have launched the app When I select a breed from the list Then I see 10 images of the breed

The App was developed in MVVM architecture with android jetpack components to make the project lifecycle aware by following the clean code architecture and added the test cases
for data layer, interactors layer, framworklayer and presentation layer. And also added ViewModel testing, fragment , navigation testing.

Technologies Used for CcaApp

1) Kotlin (Clean code architecture)
2) MVVM architecture
3) ConstrainLayout
4) ViewModel data binding
5) Navigation Graph
6) Koin Dependency Injection
7) jUnit, mock, espresso for unit testing
8) RxJava with executor and scheduler
9) Retrofit
10) Glide for loading images
