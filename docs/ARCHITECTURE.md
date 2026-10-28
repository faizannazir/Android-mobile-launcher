# Aether Launcher — Lightweight Jetpack Compose Architecture Blueprint

## 1. Executive Summary & Goals
Aether Launcher is built on a single-Activity, reactive Jetpack Compose architecture designed specifically for Google Pixel devices running Android 14+. The architecture emphasizes:
- **Zero-Latency App Launching:** Cold start < 250ms, search query latency < 5ms.
- **120Hz Frame Stability:** Hardware-accelerated Kawase dual-filter glass blur engine with graceful fallback for low-power modes.
- **Strict Process-Death Restoration:** Immutable state managed via `SavedStateHandle` and `DataStore`.

---

## 2. Component Architecture & Data Flow

```
+-------------------------------------------------------------------+
|                        MainActivity                               |
|   (Intent: ACTION_MAIN, CATEGORY_HOME, CATEGORY_DEFAULT)          |
+-------------------------------------------------------------------+
                                  |
                                  v
+-------------------------------------------------------------------+
|                       LauncherViewModel                           |
|  - Holds LauncherState (StateFlow)                                |
|  - Leverages SavedStateHandle for process death restoration        |
+-------------------------------------------------------------------+
      |                            |                         |
      v                            v                         v
+------------------+    +--------------------+    +---------------------+
| InstalledAppsRepo|    | AppSearchEngine    |    | AppWidgetHostManager|
| (PackageManager) |    | (Fuzzy Indexing)   |    | (Native Widgets)    |
+------------------+    +--------------------+    +---------------------+
      |                            |                         |
      +----------------------------+-------------------------+
                                   |
                                   v
+-------------------------------------------------------------------+
|                       HomePreferences                             |
|             (Jetpack DataStore - JSON Layout & Tokens)            |
+-------------------------------------------------------------------+
```

---

## 3. Package Structure
```
com.aether.launcher/
├── MainActivity.kt               # Entry point and HOME intent handler
├── data/
│   ├── model/                    # AppItem, FolderItem, WidgetItem, LayoutGrid
│   ├── InstalledAppsRepository.kt# Query & observe PackageManager app installs
│   ├── AppSearchEngine.kt        # Fast local search & fuzzy indexing
│   ├── HomePreferences.kt        # DataStore for grid, dock, and glass settings
│   └── WidgetHostManager.kt      # Native AppWidgetHost wrapper for Compose
├── ui/
│   ├── theme/                    # Color, Type, Shape, GlassTokens
│   ├── components/               # GlassCard, SquircleIcon, FloatingDock
│   ├── screens/                  # HomeScreen, AppDrawer, SearchOverlay, FolderModal
│   └── customization/            # CustomizationSheet, WidgetPickerSheet
├── viewmodel/
│   ├── LauncherViewModel.kt      # Central reactive ViewModel
│   └── LauncherState.kt          # Sealed UI state representation
└── glass/
    ├── GlassEngine.kt            # RenderEffect & Fallback compositor
    └── KawaseBlur.kt             # Dual-pass downsampling blur filter
```

---

## 4. Key Subsystem Implementation Details

### A. App Discovery & Search
- **Repository:** `InstalledAppsRepository` listens for `ACTION_PACKAGE_ADDED`, `REMOVED`, and `CHANGED` broadcast intents, updating a `StateFlow<List<AppItem>>`.
- **Search Engine:** `AppSearchEngine` builds an in-memory prefix trie on launch, achieving sub-5ms search matching for 200+ installed applications.

### B. Widget Hosting in Jetpack Compose
- `WidgetHostManager` wraps Android's `AppWidgetHost` (`APPWIDGET_HOST_ID = 1024`).
- Widgets are embedded into Jetpack Compose layouts using `AndroidView(factory = { appWidgetHostView })`.

### C. Persistent Home Screen Layout
- Stored via **Jetpack DataStore** as structured, compressed JSON (`layout_config.json`).
- Avoids the startup overhead of Room for small (< 50KB) layout structures.

### D. Glass Engine & Render Effect Fallback
```kotlin
@Composable
fun Modifier.glassmorphism(
    level: GlassLevel,
    isLowPowerMode: Boolean
): Modifier {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !isLowPowerMode) {
        this.graphicsLayer {
            renderEffect = RenderEffect.createBlurEffect(
                level.blurPx, level.blurPx, Shader.TileMode.CLAMP
            )
        }.background(level.color)
    } else {
        this.background(level.fallbackColor)
            .border(1.dp, level.borderColor, CircleShape)
    }
}
```

---

## 5. Performance Benchmarks & Targets

| Benchmark Metric | Target Threshold | Target Measurement Method |
| :--- | :--- | :--- |
| **Cold Startup Time** | < 250 ms | Macrobenchmark `startup(COLD)` |
| **Warm Startup Time** | < 80 ms | Macrobenchmark `startup(WARM)` |
| **Search Query Latency** | < 5 ms | Microbenchmark `AppSearchTest` |
| **App Drawer Frame Rate** | 120 FPS (0 dropped frames) | Android VSYNC Frame Metrics |
| **Memory Footprint (Idle)**| < 45 MB | Android Studio Profiler (Heap) |
| **APK Binary Size** | < 4.5 MB | R8 minified release build |

---

## 6. Implementation Roadmap
1. **Phase 1 (Core Shell):** `MainActivity`, `InstalledAppsRepository`, basic 4x5 Compose grid, and launcher intent filters.
2. **Phase 2 (Glass & Dock):** Hardware-accelerated `GlassEngine`, floating pill dock, and squircle icon rendering.
3. **Phase 3 (App Drawer & Search):** Full-screen glass app drawer, category filter chips, and `AppSearchEngine`.
4. **Phase 4 (Folders & Customization):** Shared-element folder modal animations and DataStore preferences sheet.
5. **Phase 5 (Widgets & Polishing):** Native `AppWidgetHostManager` and Macrobenchmark profiling.
