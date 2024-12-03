![MahaShein2](https://github.com/user-attachments/assets/0b2ee2a7-eb5d-444f-8526-26180875f7f6)This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
# Tools
* RoomDB with compose multiplaform
* MVVM design pattern
* compose views
* Koin dependency injection
* Navigate graph and nested compostables
* Shared UI and Modules


# App Scenario 
![MahaShein1](https://github.com/user-attachments/assets/c8cdb7d6-37bb-4d26-9a20-a19136ef442b)

![MahaShein2](https://github.com/user-attachments/assets/7460fb2a-e093-4699-952a-ea884405896b)

![MahaShein3](https://github.com/user-attachments/assets/18d4e706-4f4b-4e00-a36c-38080e3bb997)

![MahaShein4](https://github.com/user-attachments/assets/9866dd2e-61d0-41f7-92e1-2e4fa0b42335)

![MahaShein5](https://github.com/user-attachments/assets/ac39302a-c8dd-44d8-9c97-fc1b26b8e36f)

![MahaShein6](https://github.com/user-attachments/assets/cd402995-2f6b-47e2-9cad-3517b983f036)

![MahaShein7](https://github.com/user-attachments/assets/466a15d5-a7b8-46c2-85c2-c42ec4c1404c)
