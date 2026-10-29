# Aether Launcher — Design System & Visual Specification

## 1. Product Design Principles
Aether Launcher is an original, premium, lightweight Android launcher built specifically for Google Pixel flagships running Android 14+. It synthesizes:

1. **Spatial Elegance & Physical Glass Physics (HarmonyOS Influence):** Surfaces mimic precision-milled acoustic sapphire glass—weightless, airy, and translucent. Abstract pastel and mercury wallpaper tones filter softly through refractive substrate layers with real caustics and 1px specular highlights.
2. **Wallpaper-First Composition (Pixel Influence):** Interface elements float naturally over the canvas without covering it in heavy, opaque cards. Oversized clock typography and weather pills sit directly on the wallpaper with subtle ambient drop shadows for optimal contrast.
3. **Architectural Technical Character (Nothing OS Influence):** Distinctive, geometric dot-matrix/monospace typography for time displays, telemetry badges, and micro-labels, establishing a memorable, futuristic brand identity.
4. **Zero-Friction Pixel Ergonomics:** Native edge-to-edge layout, strict system inset awareness (`WindowInsets.statusBars` and `WindowInsets.navigationBars`), and comfortable 48dp+ touch targets.

---

## 2. Approved Visual Direction: Direction C (Distinctive Aether)

After exploring three distinct concepts in Google Stitch:
- **Direction A (Pixel Refined):** Light, airy porcelain/crystal museum-grade theme.
- **Direction B (Atmospheric Minimalism):** Dark Nordic aurora borealis landscape photo theme.
- **Direction C (Distinctive Aether — WINNER):** Asymmetrical spatial composition, liquid mercury/glass caustics wallpaper, architectural dot-matrix clock (`10:08`), spatial audio/telemetry header capsule, and liquid glass squircle grid.

Direction C was chosen as the flagship direction because it provides an immediately recognizable, expensive visual identity while maintaining 100% daily usability and high Jetpack Compose implementation feasibility.

---

## 3. Approved Stitch UI Screen Set Matrix

| Screen Role | Approved Screen Name in Stitch | Key Aesthetic Features |
| :--- | :--- | :--- |
| **Flagship Home Screen** | `Concept C — Aether Spatial Glass Home Screen` | Asymmetrical dot-matrix clock, telemetry header badge, liquid glass 4x2 grid, floating glass dock. |
| **App Drawer** | `Pixel Harmony App Drawer` | Full-screen glass overlay, persistent search field (`"Search 128 apps & shortcuts..."`), category filter chips, 4-column grid. |
| **App Search Overlay** | `Pixel Harmony Search Overlay` | Active search field with blinking cursor, categorized live results (*Top Matches, Actions, Contacts*), dark glass Gboard. |
| **Expanded Folder Modal** | `Pixel Harmony — Social & Media Folder Modal` | Centered floating glass container, inline edit pencil, `+ Add` button, 3x3 squircle grid with badges. |
| **Home Screen Edit Mode** | `Aether Launcher — Home Screen Edit Mode` | Scaled perspective card, 4x5 grid alignment lines, drag handles, floating glass action toolbar. |
| **Widget Placement Sheet** | `Aether Launcher — Widgets & Glanceables Sheet` | Frosted bottom sheet, search input, categorized widget cards (*Glance 4x2, Battery 2x2*) with `+ Add` buttons. |
| **Customization & Settings** | `Aether Launcher — Customization & Settings` | Theme selection cards (*Dark Glass, Light Glass, Dynamic Material You*), Glass Blur Intensity slider (0-100%), GPU Acceleration toggle. |
| **Light Theme Variant** | `Direction A — Pixel Refined Home Screen` | Luminous, airy glass over light pastel 3D crystal sculpture wallpaper. |
| **Dark Theme Variant** | `Direction B — Atmospheric Aurora Home Screen` | Cinematic Nordic aurora wallpaper with deep indigo sky and dark frosted glass overlays. |

---

## 4. Color Palette & Semantic Tokens

### Base & Accent Palette
- **Obsidian Slate (Primary Text/Icons):** `#191C1E` (Light Mode) / `#E1E2EB` (Dark Mode).
- **Electric Cyan (Primary Spatial Accent):** `#00FBFF`.
- **Pixel Frost Blue (Secondary Accent):** `#8AB4F8`.
- **Radiant Iris / Orchid (Tertiary Accent):** `#C58AF9` / `#EC4899`.
- **Crystalline Pearl (Neutral Light Base):** `#F8FAFC`.
- **Deep Obsidian (Neutral Dark Base):** `#101319`.

### Semantic Tokens
- `color.surface.glass.light`: `rgba(255, 255, 255, 0.68)`
- `color.surface.glass.dark`: `rgba(16, 19, 25, 0.78)`
- `color.border.specular`: `rgba(255, 255, 255, 0.85)`
- `color.text.primary`: `#191C1E` (Light) / `#E1E2EB` (Dark)
- `color.text.secondary`: `rgba(25, 28, 30, 0.65)`
- `color.accent.cyan`: `#00FBFF`

---

## 5. Glass Material System

The launcher defines three reusable material levels:

### Level 1: Subtle Glass
- **Intended Usage:** Telemetry headers, At-a-Glance weather/date chips, micro badges, inactive category chips.
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
| `display.hero.clock` | Monospace (Dot-Matrix Style) | 76 | 80 | 700 (Bold) | -0.04em |
| `headline.lg` | Plus Jakarta Sans | 32 | 38 | 600 (SemiBold) | -0.02em |
| `headline.md` | Plus Jakarta Sans | 22 | 28 | 600 (SemiBold) | -0.015em |
| `title.sm` | Monospace | 14 | 20 | 700 (Bold) | 0.08em |
| `body.lg` | Plus Jakarta Sans / Inter | 16 | 24 | 400 (Regular) | 0em |
| `body.md` | Plus Jakarta Sans / Inter | 14 | 20 | 400 (Regular) | 0em |
| `label.md` | Plus Jakarta Sans | 12 | 16 | 600 (SemiBold) | 0.02em |
| `label.small` | Monospace | 10 | 14 | 500 (Medium) | 0.05em |

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

---

## 8. Motion & Animation Language

- **App Drawer Opening:** Vertical spring transition (`dampingRatio = 0.8f`, `stiffness = 350f`) with linear Gaussian blur scale from 0 to 32dp.
- **Folder Expansion:** Shared element scale transform from 56dp squircle icon to 320dp centered glass modal card with 150ms wallpaper dimming.
- **Dock Touch Feedback:** Micro scale compression (`scale(0.96f)`) with top specular highlight flare (`alpha 0.4 -> 0.8`).
- **Reduced Motion Support:** Fully honors system animation scale (`AnimatorDurationScale = 0`), instantly swapping spring transitions for 150ms alpha fades.

---

## 9. Accessibility & System Constraints

- **WCAG 2.1 AA Compliance:** Minimum 4.5:1 text contrast guaranteed via wallpaper ambient shadow underlays and dynamic scrims.
- **Touch Targets:** Minimum 48dp × 48dp hit zones for all interactive buttons and icons.
- **System Insets:** Strict `WindowInsets.statusBars` and `WindowInsets.navigationBars` handling in Jetpack Compose.
