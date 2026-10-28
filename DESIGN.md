# Aether Launcher — Design System Specification

## 1. Product Design Principles
Aether Launcher is a lightweight, premium Android launcher optimized for Google Pixel phones. Its core design philosophy rests on three pillars:

1. **Spatial Elegance & Physical Glass Physics:** Surfaces behave like precision-milled acoustic glass—weightless, airy, and translucent. Abstract pastel wallpaper tones filter softly through refractive substrate layers, while razor-sharp deep charcoal typography guarantees immediate legibility.
2. **Wallpaper-First Composition:** The wallpaper is the visual hero. Interface elements float naturally over the canvas without covering it in heavy, opaque cards.
3. **Zero-Friction Pixel Ergonomics:** Native to Android 14+ gesture navigation, strict system inset awareness, and comfortable 48dp+ touch targets.

---

## 2. Approved Visual Directions & References
- **Huawei HarmonyOS:** Spatial depth, multi-layered glass materials, translucent floating surfaces, and optical light caustics.
- **Google Pixel / Material You:** Wallpaper-first composition, readable geometry, dynamic color extraction, and zero-latency usability.
- **Nothing OS:** Distinctive typographical voice, restrained visual character, and clean layout grid.

---

## 3. Approved Stitch UI Screens Matrix

| Screen Role | Approved Screen Title in Stitch | Key Aesthetic Features |
| :--- | :--- | :--- |
| **Flagship Home Screen** | `Aether Launcher — Flagship Crystal Home Screen` | Oversized `10:08` clock, 3D glass wallpaper, 4x2 squircle grid, floating glass dock. |
| **App Drawer** | `Pixel Harmony App Drawer` | Full-screen glass overlay, persistent search, category chips (*Social, Media*), 4-column app grid. |
| **App Search** | `Pixel Harmony Search Overlay` | Active search input with blinking cursor, categorized live results (*Top Matches, Actions, Contacts*), Gboard dark glass. |
| **Expanded Folder Modal** | `Pixel Harmony — Social & Media Folder Modal` | Centered floating glass container, inline edit pencil, `+ Add` button, 3x3 squircle grid with badges. |
| **Home Screen Edit Mode** | `Aether Launcher — Home Screen Edit Mode` | Scaled perspective card, 4x5 grid alignment lines, resize/drag handles, floating glass action toolbar. |
| **Widget Placement Sheet** | `Aether Launcher — Widgets & Glanceables Sheet` | Frosted bottom sheet, search input, categorized widget cards (*Glance 4x2, Battery 2x2*) with `+ Add` buttons. |
| **Customization & Settings** | `Aether Launcher — Customization & Settings` | Theme selection cards (*Dark Glass, Light Glass, Dynamic*), Glass Blur Intensity slider (0-100%), GPU Acceleration toggle. |
| **Light Theme Variant** | `Concept A — Crystal Harmony Home Screen` | Luminous, airy glass over light pastel 3D crystal sculpture wallpaper. |
| **Dark Theme Variant** | `Concept B — Aurora Home Screen` | Cinematic Nordic aurora wallpaper with deep indigo sky and dark frosted glass overlays. |
| **Experimental Direction** | `Concept C — Liquid Space Home Screen` | Avant-garde asymmetrical layout with suspended mercury chrome and optical glass caustics. |

---

## 4. Color Palette & Semantic Tokens

### Base & Accent Palette
- **Obsidian Slate (Primary Text/Icons):** `#191c1e` (Light Mode) / `#e1e2eb` (Dark Mode).
- **Electric Sky (Secondary Accent):** `#2563eb` / `#8ab4f8`.
- **Luminous Orchid (Tertiary Accent):** `#ec4899` / `#c58af9`.
- **Crystalline Pearl (Neutral Light Base):** `#f8fafc`.
- **Deep Obsidian (Neutral Dark Base):** `#101319`.

### Semantic Tokens
- `color.surface.glass.light`: `rgba(255, 255, 255, 0.68)`
- `color.surface.glass.dark`: `rgba(16, 19, 25, 0.78)`
- `color.border.specular`: `rgba(255, 255, 255, 0.85)`
- `color.text.primary`: `#191c1e`
- `color.text.secondary`: `rgba(25, 28, 30, 0.65)`
- `color.text.inverse`: `#f8fafc`

---

## 5. Glass Material System

The launcher defines three reusable material levels:

### Level 1: Subtle Glass
- **Intended Usage:** At-a-Glance weather/date chips, micro badges, inactive category chips.
- **Opacity:** `rgba(255, 255, 255, 0.45)` (Light) / `rgba(255, 255, 255, 0.08)` (Dark).
- **Blur Behavior:** `backdrop-filter: blur(16px)`.
- **Border:** `1px solid rgba(255, 255, 255, 0.40)`.
- **Shadow:** None / `0 2px 6px rgba(0,0,0,0.04)`.
- **Low-Cost Fallback:** `rgba(240, 243, 246, 0.80)` solid fill.

### Level 2: Balanced Glass
- **Intended Usage:** Floating Dock, Search Fields, Widget Cards, Home Screen Toolbars.
- **Opacity:** `rgba(255, 255, 255, 0.72)` (Light) / `rgba(25, 30, 40, 0.58)` (Dark).
- **Blur Behavior:** `backdrop-filter: blur(28px) saturate(180%)`.
- **Border:** `1px solid rgba(255, 255, 255, 0.85)` (top edge specular highlight).
- **Shadow:** `0 12px 32px -4px rgba(0, 0, 0, 0.12), inset 0 1px 1px rgba(255, 255, 255, 0.9)`.
- **Low-Cost Fallback:** `rgba(255, 255, 255, 0.92)` with 1px border.

### Level 3: Strong Glass
- **Intended Usage:** App Drawer Overlays, Expanded Folder Modals, Customization & Settings Sheets.
- **Opacity:** `rgba(255, 255, 255, 0.88)` (Light) / `rgba(16, 19, 25, 0.82)` (Dark).
- **Blur Behavior:** `backdrop-filter: blur(44px) saturate(200%)`.
- **Border:** `1.5px solid rgba(255, 255, 255, 0.70)`.
- **Shadow:** `0 20px 50px rgba(0, 0, 0, 0.35)`.
- **Low-Cost Fallback:** `rgba(20, 24, 32, 0.95)` solid dark surface.

---

## 6. Typography Hierarchy

| Token Name | Family | Size (sp) | Line Height (sp) | Weight | Tracking |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `display.hero.clock` | Plus Jakarta Sans / Google Sans | 72 | 76 | 300 (Light) | -0.04em |
| `headline.lg` | Plus Jakarta Sans | 32 | 38 | 600 (SemiBold) | -0.02em |
| `headline.md` | Plus Jakarta Sans | 22 | 28 | 600 (SemiBold) | -0.015em |
| `title.sm` | Plus Jakarta Sans | 16 | 22 | 600 (SemiBold) | -0.01em |
| `body.lg` | Plus Jakarta Sans / Inter | 16 | 24 | 400 (Regular) | 0em |
| `body.md` | Plus Jakarta Sans / Inter | 14 | 20 | 400 (Regular) | 0em |
| `label.md` | Plus Jakarta Sans | 12 | 16 | 600 (SemiBold) | 0.02em |
| `label.sm` | Plus Jakarta Sans | 11 | 14 | 500 (Medium) | 0.03em |

---

## 7. Spacing, Radii, and Layout Grid

### Spacing Scale (8dp Base Increment)
- `space.xs`: `4dp`
- `space.sm`: `8dp`
- `space.md`: `14dp`
- `space.lg`: `20dp`
- `space.xl`: `32dp`

### Corner Radii
- `radius.sm`: `8dp` (Micro badges, tags)
- `radius.md`: `16dp` (Sub-cards, widget toggles)
- `radius.lg`: `22dp` (Squircle app icons - 22% continuous curvature)
- `radius.xl`: `32dp` (Folder modals, bottom sheet tops)
- `radius.full`: `9999dp` (Capsule dock, search field pills, action buttons)

### Grid Metrics
- **Desktop Grid:** 4 columns × 5 rows (Compact) / 5 columns × 5 rows (Standard).
- **Icon Sizing:** 56dp × 56dp icon target with 48dp minimum hit zone.
- **Dock Elevation:** 16dp above navigation gesture bar.

---

## 8. Layout Specifications

### A. Home Screen
- **Status Bar:** Inset `24dp` top padding; time, 5G, Wi-Fi, battery.
- **Upper Glance Zone:** Unbounded clock digits `10:08` floating over wallpaper, paired with date line and compact weather pill (`72° Sunny`).
- **App Grid:** 4x2 matrix of squircle icons with clean 12sp labels and subtle drop shadows.
- **Dock:** Floating capsule pill holding 5 apps (*Phone, Chat, Chrome, Camera, App Drawer*).

### B. App Drawer
- **Scrim:** Full-screen Level 3 glass overlay over blurred wallpaper.
- **Search:** Top capsule input field with `"Search 128 apps & shortcuts..."`.
- **Category Chips:** Horizontal scrollable list (*All, Social, Productivity, Media, Utilities*).
- **Frequent Apps:** Top quick-launch row of 4 icons.
- **App Matrix:** 4-column alphabetical grid with right-side A-Z fast scrubber.

### C. Search & Folders
- **Search Overlay:** Instant live results categorized into *Top Matches*, *App Actions*, *Contacts*, and *Web Suggestions*, anchored above dark glass Gboard.
- **Expanded Folder:** Centered floating Level 3 glass card (`320dp` width) with 3x3 app grid, title, edit pencil, and `+ Add` button.

---

## 9. Motion & Animation Language

- **App Drawer Opening:** Vertical spring transition (`dampingRatio = 0.8f`, `stiffness = 350f`) with linear Gaussian blur scale from 0 to 32dp.
- **Folder Expansion:** Shared element scale transform from 56dp squircle icon to 320dp centered glass modal card with 150ms wallpaper dimming.
- **Dock Touch Feedback:** Micro scale compression (`scale(0.96f)`) with top specular highlight flare (`alpha 0.4 -> 0.8`).
- **Reduced Motion Support:** Fully honors system animation scale (`AnimatorDurationScale = 0`), instantly swapping spring transitions for 150ms alpha fades.

---

## 10. Accessibility & Android Constraints

- **WCAG 2.1 AA Compliance:** Minimum 4.5:1 text contrast guaranteed via wallpaper ambient shadow underlays and dynamic scrims.
- **Touch Targets:** Minimum 48dp × 48dp hit zones for all interactive buttons and icons.
- **System Insets:** Strict `WindowInsets.statusBars` and `WindowInsets.navigationBars` handling in Jetpack Compose.
