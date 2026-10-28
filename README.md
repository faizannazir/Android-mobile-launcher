# Aether Launcher — Next-Generation Android Launcher for Google Pixel

Aether Launcher is a lightweight, ultra-fast, native Android launcher optimized for Google Pixel devices. It combines the spatial glassmorphism and depth of **Huawei HarmonyOS** with the wallpaper-first simplicity and zero-latency usability of **Google Pixel Material You**, accompanied by **Nothing OS-inspired typography**.

---

## Key Features

- **Wallpaper-First Home Screen:** 3D crystal sculpture ambient background with an oversized `10:08` geometric clock floating directly on the wallpaper (no rectangular card boundaries).
- **Floating Translucent Glass Dock:** Physical glass container holding 5 quick-access favorite apps with multi-pass Gaussian blur, top specular highlight, and realistic ground shadow.
- **App Drawer & Category Filtering:** Full-screen Level 3 glass overlay with search, quick category chips (*Social, Productivity, Media, Utilities*), and alphabetical app list.
- **Responsive App Search:** Sub-5ms fuzzy search for installed applications with Gboard-inspired dark glass keyboard overlay.
- **Home Screen Customization & Drag-and-Drop:** Long-press to edit, reorder apps/widgets, and auto-create folders by dropping an app onto another.
- **Native Android Widgets:** Integrated `AppWidgetHost` and `AppWidgetManager` with `WidgetPickerSheet` for placing, configuring, and restoring real system widgets.
- **Jetpack DataStore Layout Persistence:** Stores pages, folders, dock items, and widget IDs safely without frame lag during animations.
- **3-Tier Glass Material Engine:** Hardware-accelerated `RenderEffect` Kawase blur on API 31+ with automatic low-power/battery saver fallback surfaces.

---

## Build Prerequisites

- **Java Development Kit (JDK):** Version 21 (OpenJDK / Temurin).
- **Android SDK:** Compile SDK 35, Min SDK 26, Target SDK 35.
- **Gradle:** Version 8.8 (using Kotlin 1.9.22).

---

## Local Development & Build Commands

### Assemble Debug APK
```bash
./gradlew assembleDebug
```
Output APK path: `app/build/outputs/apk/debug/app-debug.apk`

### Run Unit Tests
```bash
./gradlew test
```

### Build Release APK
```bash
./gradlew assembleRelease
```

---

## GitHub Actions CI & Release Workflows

- **CI Workflow (`.github/workflows/android-ci.yml`):** Automatically runs unit tests and builds the debug APK artifact on pull requests and pushes to `main`.
- **Release Workflow (`.github/workflows/android-release.yml`):** Automatically triggers on git tags (`v*`), builds the release APK, signs it using optional repository secrets (`KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`), and creates a GitHub Release with changelog artifacts.

---

## Project Documentation

- [`DESIGN.md`](DESIGN.md): Design system tokens, color palettes, glass material levels, and typography scales.
- [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md): Jetpack Compose reactive architecture, ViewModel, DataStore persistence, and widget host integration.
- [`docs/DESIGN_VALIDATION.md`](docs/DESIGN_VALIDATION.md): Test matrix, screenshot validation framework, and benchmark results.
