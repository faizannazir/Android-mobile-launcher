# Aether Launcher — Design Specification & Architectural Report

## 1. Overview & Vision
Aether Launcher is a premium, lightweight Android launcher engineered specifically for Google Pixel devices. It synthesizes:
- **Huawei HarmonyOS**: Spatial depth, physical glass materials, translucent floating surfaces, and optical light caustics.
- **Google Pixel / Material You**: Wallpaper-first composition, readable geometry, dynamic color extraction, and zero-latency usability.
- **Nothing OS**: Distinctive typographical voice, restrained visual character, and clean layout grid.

---

## 2. Architecture & Performance Guidelines

### Single-Activity Jetpack Compose Architecture
- **View Model & Lifecycle**: Single-Activity using Jetpack Compose with a reactive `LauncherViewModel` exposing immutable `LauncherState` flows (`StateFlow`).
- **App Management**: Asynchronous querying of installed applications via Kotlin Coroutines and `PackageManager`.
- **System Inset Handling**: Strict edge-to-edge support using `WindowInsets.statusBars` and `WindowInsets.navigationBars` for pixel-accurate layout across all Pixel form factors.

### 3-Tier Glass Engine
To maintain 120Hz smooth scrolling without frame drops or excessive battery drain:
1. **Tier 1 (Android 12+ / Hardware Accelerated):**
   - Utilizes `Modifier.graphicsLayer { renderEffect = RenderEffect.createBlurEffect(...) }` with Kawase dual-filter downsampling.
2. **Tier 2 (Fallback / Battery Saver Mode):**
   - Translucent gradient fill (`alpha = 0.75f`), 1px top specular stroke border, and localized static bitmap blur.
3. **Tier 3 (Reduced Motion / Low Memory):**
   - Opaque / semi-transparent Material surface without Gaussian backdrop blur.

---

## 3. Stitch Design Exploration & Suite

### Explored Concepts
1. **Concept A — Crystal (Selected Base):** Airy, museum-grade aesthetic featuring 3D translucent crystal and porcelain sculpture wallpaper, floating clock typography, and 4x2 squircle app grid.
2. **Concept B — Aurora:** Cinematic Nordic fjord aurora landscape wallpaper with deep indigo skies and dark frosted glass overlays.
3. **Concept C — Liquid Space:** Asymmetrical avant-garde composition with liquid mercury chrome droplets and optical glass caustics.

### Generated Screen Set (Stitch Assets)
- **Flagship Home Screen (`Aether Launcher — Flagship Crystal Home Screen`):** Full-bleed 3D glass wallpaper, oversized floating clock digits (`10:08`), date (`Tuesday, October 24`), weather chip, 8 squircle apps, and floating translucent glass dock with 5 favorite apps.
- **App Drawer Screen (`Pixel Harmony App Drawer`):** Full-screen frosted glass overlay (`backdrop-blur-3xl`), persistent search field (`"Search 128 apps & shortcuts..."`), category filter chips (*All, Social, Productivity, Media, Utilities*), frequent apps row, and 4-column app grid.
- **App Search Overlay (`Pixel Harmony Search Overlay`):** Active search input with blinking glowing cursor, live categorized results (*Top Matches, App Actions, Contacts, Web Search*), and integrated dark glass Gboard-inspired keyboard.
- **Expanded Folder Modal (`Pixel Harmony — Social & Media Folder Modal`):** Centered floating frosted glass container (*Social & Media*), inline rename pencil, `+ Add` button, `Done` pill, and 3x3 squircle app grid with unread badges.
- **Customization & Settings Sheet (`Aether Launcher — Customization & Settings`):** Frosted bottom sheet modal with Theme selection cards (*Dark Glass, Light Glass, Dynamic Material You*), Glass Blur Intensity slider (0-100%), GPU Acceleration toggle, and Icon Style selector (*Pixel Mono, Original, Harmony Gloss*).

---

## 4. Design System Tokens & Glass Substrates

### Glass Intensity Levels
- **Level 1 (Subtle):** `rgba(255, 255, 255, 0.45)`, 16px blur — Used for At-a-Glance weather/date chips and secondary tags.
- **Level 2 (Balanced):** `rgba(255, 255, 255, 0.68)`, 28px blur, `1px solid rgba(255, 255, 255, 0.85)` top specular highlight — Used for the Floating Dock and Search Field.
- **Level 3 (Strong / Overlay):** `rgba(16, 19, 25, 0.78)`, 48px blur, `1px solid rgba(255, 255, 255, 0.12)` — Used for Dark Theme App Drawer, Folder Modals, and Customization Sheets.

### Color Tokens
- **Primary Text / Digits:** `#191c1e` (Light Glass) / `#e1e2eb` (Dark Glass).
- **Secondary Accent:** Electric Sky `#2563eb` / Pixel Frost Blue `#8ab4f8`.
- **Tertiary Accent:** Luminous Orchid `#ec4899` / Radiant Iris `#c58af9`.
- **Neutral Base:** Crystalline Pearl `#f8fafc` / Deep Obsidian `#101319`.

### Typography
- **Clock Display:** Plus Jakarta Sans / Google Sans Light (Weight 300), `72sp`, line height `76sp`, `-0.04em` tracking.
- **Headlines / Dates:** Plus Jakarta Sans Semi-Bold (Weight 600), `16sp`, line height `22sp`.
- **App Labels:** Plus Jakarta Sans / Inter Medium (Weight 500), `12sp`, line height `16sp`.

### Radii & Spacing
- **App Icon Squircle:** `22dp` continuous curvature.
- **Dock & Search Bar:** Capsule pill (`9999dp`).
- **Modal Containers:** `32dp` rounded corners.
- **Outer Insets:** `20dp` side margins, `8dp` baseline rhythm.

---

## 5. Animation & Interaction Specifications
- **App Drawer Transition:** Vertical spring (`dampingRatio = 0.8f`, `stiffness = 350f`) with backdrop blur scaling from 0px to 32px.
- **Folder Modal Open:** Shared-element scale transform from 56dp squircle icon to 320dp centered glass modal card with 150ms wallpaper dimming.
- **Dock Touch Feedback:** Micro scale compression (`scale(0.96f)`) with top specular highlight flare (`alpha 0.4 -> 0.8`).
- **Reduced Motion:** Fully honors system animation scale, instantly swapping spring transitions for 150ms alpha fades.
