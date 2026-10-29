# Aether Launcher — HarmonyOS 4.0 / NEXT Design Specification

## 1. Executive Summary & Design Principles
Aether Launcher is a premium, lightweight native Android launcher optimized for Google Pixel flagships running Android 14+. It merges:
- **Huawei HarmonyOS 4.0 / NEXT:** Spatial depth, 2x2 Service Widgets, 2x2 Smart Expanded Folders, 22% squircle geometry, physical glass materials, and volumetric light caustics.
- **Google Pixel / Material You:** Wallpaper-first composition, readable geometry, dynamic color extraction, and zero-latency usability.
- **Nothing OS:** Distinctive, technical monospace and dot-matrix typography for clock faces, telemetry badges, and micro-labels.

---

## 2. Verified Visual References & Stitch Screen Inventory

| Screen Role | Approved Screen Title in Stitch | Key Aesthetic & Architectural Features |
| :--- | :--- | :--- |
| **Flagship Home Screen** | `HarmonyOS 4.0 Flagship Home Screen` | 3D golden sunrise glass wallpaper, 2x2 Smart Schedule Service Widget, 2x2 Smart Expanded Folder, squircle grid, floating glass dock. |
| **App Drawer** | `Pixel Harmony App Drawer` | Full-screen Level 3 glass overlay, persistent search field (`"Search 128 apps & shortcuts..."`), category filter chips, 4-column app grid. |
| **App Search Overlay** | `Pixel Harmony Search Overlay` | Active search input with blinking cursor, categorized live results (*Top Matches, Actions, Contacts*), dark glass Gboard. |
| **Expanded Folder Modal** | `Pixel Harmony — Social & Media Folder Modal` | Centered floating glass container, inline edit pencil, `+ Add` button, 3x3 squircle grid with badges. |
| **Home Screen Edit Mode** | `Aether Launcher — Home Screen Edit Mode` | Scaled perspective card, 4x5 grid alignment lines, drag handles, floating glass action toolbar. |
| **Widget Placement Sheet** | `Aether Launcher — Widgets & Glanceables Sheet` | Frosted bottom sheet, search input, categorized widget cards (*Glance 4x2, Battery 2x2*) with `+ Add` buttons. |
| **Customization & Settings** | `Aether Launcher — Customization & Settings` | Theme selection cards (*Dark Glass, Light Glass, Dynamic Material You*), Glass Blur Intensity slider (0-100%), GPU Acceleration toggle. |

---

## 3. HarmonyOS Service Widgets & Smart 2x2 Folders

### A. 2x2 Smart Schedule Service Widget
- **Geometry:** 2x2 grid tile (`152dp x 152dp`), `26dp` continuous squircle corners.
- **Surface:** Level 2 Glass (`backdrop-blur-xl`, `rgba(255, 255, 255, 0.72)` light / `rgba(25, 30, 40, 0.58)` dark) with 1px top specular white rim highlight.
- **Interiors:** Event title (*Design Sync · 10:30 AM*), subtitle (*Conf Room 4*), live progress bar, and interactive quick action toggles (*Focus*, *Mute*).

### B. 2x2 Smart Expanded Folder
- **Geometry:** 2x2 grid tile (`152dp x 152dp`), `26dp` continuous squircle corners.
- **Direct App Launching:** Houses 4 direct-launch app icons arranged in a 2x2 inner matrix (`38dp x 38dp` touch targets). Tapping an inner icon launches the app directly without opening the modal; tapping the title or blank space opens the expanded folder modal.

---

## 4. Color Palette & Semantic Tokens

### Base & Accent Palette
- **Obsidian Slate (Primary Text/Icons):** `#191C1E` (Light) / `#E1E2EB` (Dark).
- **Electric Cyan (Primary Spatial Accent):** `#00FBFF`.
- **Pixel Frost Blue (Secondary Accent):** `#8AB4F8`.
- **Radiant Iris / Orchid (Tertiary Accent):** `#C58AF9` / `#EC4899`.
- **Warm Champagne Gold (Wallpaper Accent):** `#F59E0B`.

---

## 5. Glass Material Engine & Fallbacks

- **Level 1 (Subtle):** `rgba(255, 255, 255, 0.45)`, 16px blur — Used for At-a-Glance chips.
- **Level 2 (Balanced):** `rgba(255, 255, 255, 0.72)`, 28px blur, `1px solid rgba(255, 255, 255, 0.85)` — Used for 2x2 Service Widgets, 2x2 Smart Folders, Floating Dock, and Search Fields.
- **Level 3 (Strong / Overlay):** `rgba(255, 255, 255, 0.88)` / `rgba(16, 19, 25, 0.82)`, 44px blur — Used for App Drawer, Folder Modals, and Settings Sheets.
- **Fallback Surface:** When GPU blur is disabled or on battery saver, engine seamlessly substitutes solid translucent surfaces (`rgba(240, 243, 246, 0.92)`).
