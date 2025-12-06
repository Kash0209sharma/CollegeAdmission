# College Admission Android App

A comprehensive Android application designed to help students navigate the college admission process. The app provides information about courses, universities, colleges, news updates, and features an AI-powered chatbot for admission guidance.

## Features

### 🔐 Authentication
- User registration and login using Firebase Authentication
- Secure password management
- User profile management

### 📚 Course Information
- Browse available courses and programs
- Detailed course information
- Course categories and specializations

### 🏛️ University & College Directory
- Comprehensive list of universities
- College information and details
- Search and filter capabilities

### 📰 News & Updates
- Latest admission news and announcements
- Important dates and deadlines
- Admission-related updates

### 🤖 AI Chatbot
- Interactive chatbot for admission queries
- Real-time assistance
- Quick access via app shortcuts

### 👤 User Profile
- Personal information management
- Profile customization
- User preferences

### 🎨 Modern UI/UX
- Material Design components
- Dark mode support
- Smooth animations with Lottie
- Bottom navigation for easy access

## Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI**: Material Design Components, View Binding
- **Backend**: Firebase
  - Firebase Authentication
  - Cloud Firestore
  - Firebase Messaging
  - Firebase Analytics
- **Database**: Room Database (Local storage)
- **Dependency Injection**: KAPT
- **Asynchronous Operations**: Kotlin Coroutines
- **JSON Parsing**: Gson
- **Animations**: Lottie
- **Background Tasks**: WorkManager

## Requirements

- Android Studio Hedgehog or later
- JDK 11 or higher
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 36
- Gradle 8.0+

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/CollegeAdmission.git
cd CollegeAdmission
```

### 2. Firebase Setup
1. Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
2. Add an Android app to your Firebase project
3. Download `google-services.json` from Firebase Console
4. Place the `google-services.json` file in the `app/` directory
   - **Note**: `google-services.json` is excluded from version control for security reasons

### 3. Build the Project
1. Open the project in Android Studio
2. Sync Gradle files
3. Build the project (Build > Make Project)

### 4. Run the App
1. Connect an Android device or start an emulator
2. Click Run (or press Shift+F10)

## Project Structure

```
CollegeAdmission/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/app/collegeadmissionapp/
│   │   │   │   ├── adapter/          # RecyclerView adapters
│   │   │   │   ├── data/              # Data models
│   │   │   │   ├── repository/        # Data repositories
│   │   │   │   ├── ui/                # Activities
│   │   │   │   ├── utils/             # Utility classes
│   │   │   │   └── viewmodel/         # ViewModels
│   │   │   ├── res/                    # Resources (layouts, drawables, values)
│   │   │   └── AndroidManifest.xml
│   │   ├── androidTest/                # Instrumented tests
│   │   └── test/                       # Unit tests
│   ├── build.gradle.kts
│   └── google-services.json           # (Not in version control)
├── gradle/
│   └── wrapper/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## Key Activities

- **MainActivity**: Splash screen with authentication check
- **LoginActivity**: User login
- **RegisterActivity**: User registration
- **DashboardActivity**: Main dashboard with navigation cards
- **CoursesActivity**: Course listings and details
- **UniversitiesActivity**: University directory
- **CollegesActivity**: College listings
- **NewsActivity**: News and updates
- **ChatbotActivity**: AI chatbot interface
- **ProfileActivity**: User profile management

## App Shortcuts

The app supports Android app shortcuts for quick access to:
- Chatbot
- Latest News

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

Kashyap Sharma

## Contact

For questions or support, please open an issue in the repository.

---

**Note**: Make sure to add your own `google-services.json` file from Firebase Console before building the project.

