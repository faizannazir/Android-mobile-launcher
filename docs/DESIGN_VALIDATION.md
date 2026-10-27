# Aether Launcher — Design Validation & Quality Assurance Framework

## 1. Overview
This document specifies the repeatable process for validating that native Android Jetpack Compose implementations accurately match the approved Google Stitch UI designs for Aether Launcher.

---

## 2. Repeatable Validation Workflow

```
+------------------------+      +------------------------+
|  Stitch Design Asset   |      | Android Compose Output |
|  (Approved Screenshot) |      | (Pixel Device / AVD)   |
+------------------------+      +------------------------+
            |                               |
            v                               v
+--------------------------------------------------------+
|           Pixel-by-Pixel Screenshot Diff              |
|        (Automated Playwright / ImageMagick)           |
+--------------------------------------------------------+
                            |
                            v
+--------------------------------------------------------+
|             Visual Fidelity Audit Checklist            |
+--------------------------------------------------------+
```

### Steps:
1. **Golden Asset Export:** Export high-resolution PNGs of approved Stitch screens from project `12705472190371181985`.
2. **Device Capture:** Capture uncompressed 1080x2400 PNG screenshots from a Google Pixel 8 AVD / physical device running the Compose build.
3. **Automated Diff Analysis:** Run image comparison scripts (`compare -metric AE -fuzz 5% golden.png actual.png diff.png`) to highlight layout mismatches.
4. **Visual Audit Checklist Execution:** Review against the checklist below.

---

## 3. Visual Fidelity Checklist

### A. Layout & Spacing
- [ ] Clock digits (`10:08`) floating directly over wallpaper without bounding box.
- [ ] Outer margins strictly 20dp on mobile viewports.
- [ ] App grid 4×2 layout perfectly centered with 14dp icon-to-label vertical clearance.
- [ ] Floating dock positioned 16dp above system gesture bar.

### B. Glass Material & Optics
- [ ] Level 1, 2, and 3 glass levels displaying correct backdrop blur (16px, 28px, 44px).
- [ ] Dock pill exhibiting 1px white/85% top specular highlight rim.
- [ ] Multi-stage soft drop shadow underneath dock and folder cards.
- [ ] Fallback translucent surfaces activating gracefully when battery saver is enabled.

### C. Typography & Color
- [ ] Clock numerals using Plus Jakarta Sans / Google Sans Light (72sp / 300 weight).
- [ ] High contrast text legibility maintained across both light porcelain and dark aurora wallpapers.
- [ ] Correct semantic color tokens (`#191c1e` obsidian, `#2563eb` electric sky, `#ec4899` orchid).

### D. Android System Insets
- [ ] Status bar insets (24dp) preventing camera punch-hole overlap with status indicators.
- [ ] Navigation bar insets (16dp) padding gesture indicator line safely below the floating dock.

---

## 4. Fix Prioritization Framework

Issues discovered during visual validation are categorized and prioritized as follows:

| Priority Level | Criteria | Required Action |
| :--- | :--- | :--- |
| **P0 — Critical** | Obstructed touch targets, overlapping text, broken status/nav insets, unreadable contrast. | Must fix immediately before code merge. |
| **P1 — High** | Incorrect glass blur radius (> 8px deviation), wrong font family/weight, misaligned dock. | Fix in current sprint iteration. |
| **P2 — Medium** | Micro-shadow opacity variation (< 10%), slight border color tint offset. | Polish in subsequent pass. |
