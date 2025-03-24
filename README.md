# SilentApp

SilentApp is a simple Android application that allows you to quickly toggle the Do Not Disturb (DnD) mode on your device and manage the permission required to change the notifications status mode. When toggled on, the app always sets the DnD mode to "Priority notifications" mode, and when toggled off, it restores the normal notifications mode.

## Features

- **Toggle DnD Mode:**  
  Switch between enabling and disabling Do Not Disturb mode. When enabled, the app sets the DnD mode to "Priority notifications" (allowing only priority notifications), and when disabled, it restores the normal notifications settings.

- **Permission Management:**  
  Open the system settings directly from the app to grant or modify the permission needed to control the notifications status mode.

## Prerequisites

Before you start, ensure you have the following:

- A computer with Windows, macOS, or Linux.
- A USB cable to connect your Android phone to your computer.
- An Android phone running Android 6.0 (Marshmallow) or later.
- Internet connection to download required software.

## Installing Android Studio

Android Studio is the official IDE for Android development. Follow these steps to install it:

1. **Download Android Studio:**
    - Visit the [Android Studio download page](https://developer.android.com/studio) and download the installer for your operating system.
2. **Install Android Studio:**
    - Run the downloaded installer and follow the on-screen instructions.
    - During installation, make sure to install the Android SDK and other required tools that are prompted.
3. **Launch Android Studio:**
    - After installation, open Android Studio. It may take a few minutes on the first launch to set up necessary components.

## Setting Up Your Android Phone

To test and run the application on your own device, you need to set up your Android phone for development:

1. **Enable Developer Options:**
    - On your Android device, go to **Settings** > **About phone**.
    - Tap on **Build number** 7 times until you see a message saying "You are now a developer".
2. **Enable USB Debugging:**
    - In **Settings**, go to **System** > **Developer options**.
    - Find and enable **USB debugging**.
3. **Connect Your Phone:**
    - Use a USB cable to connect your phone to your computer.
    - On your phone, you may see a prompt asking to allow USB debugging. Tap **Allow**.

## Building and Installing the App

Follow these steps to build and install SilentApp on your device:

1. **Clone or Download the Project:**
    - Clone the repository using Git:
      ```bash
      git clone https://github.com/andrewgrow/SilentApp.git
      ```
      Or download the ZIP archive and extract it.
2. **Open the Project in Android Studio:**
    - Launch Android Studio.
    - Click on **Open an existing Android Studio project**.
    - Navigate to the project folder and select it.
3. **Build the Project:**
    - Once the project is loaded, let Gradle sync and build the project.
    - Click on the **Build** menu and select **Make Project**.
4. **Run the App on Your Device:**
    - Click the **Run** button (the green triangle) on the toolbar.
    - In the **Select Deployment Target** dialog, choose your connected device.
    - Click **OK**. Android Studio will compile the app and install it on your device.

## Usage

- **Toggle DnD Mode:**  
  Use the provided quick settings tile or the app interface to switch between enabling and disabling the Do Not Disturb mode. When enabled, only priority notifications are allowed.

- **Manage Permission:**  
  If the app does not have the necessary permission to change the notifications status mode, simply tap the permission screen to open the system settings and grant the required access.

## Troubleshooting

- **Device Not Recognized:**
    - Ensure USB debugging is enabled.
    - Try using a different USB cable or port.
    - Check if your device’s drivers are correctly installed on your computer.
- **Gradle Sync Issues:**
    - Make sure you have a stable internet connection.
    - In Android Studio, go to **File > Invalidate Caches / Restart…**.

## Additional Resources

- [Android Studio Documentation](https://developer.android.com/studio/intro)
- [Developer Options on Android](https://developer.android.com/studio/debug/dev-options)
- [USB Debugging Information](https://developer.android.com/studio/debug/dev-options#enable)

---

Happy coding! If you have any questions or issues, feel free to open an issue on the project repository.