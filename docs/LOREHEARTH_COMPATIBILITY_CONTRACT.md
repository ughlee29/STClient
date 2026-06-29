# LoreHearth — Compatibility Contract

## Final Target

LoreHearth aims for SillyTavern-style workflow parity plus Android-native improvements.

The goal is not to copy another project. The goal is to support common user workflows and content formats in a clean Android-native app.

## Positioning

LoreHearth final target:

**SillyTavern-compatible power, Android-native simplicity, and offline-ready roleplay.**

## Core Parity Goals

LoreHearth should eventually support:

- Character cards.
- Character library.
- User personas.
- Scenario and first-message handling.
- Generation settings and presets.
- OpenAI-compatible cloud providers.
- Lorebooks / World Info equivalent.
- Prompt construction and prompt inspection.
- Regex processing.
- Branching and regeneration.
- Group chat.
- Auto-summary and memory tools.
- RAG / document retrieval.
- TTS.
- Translation.
- Image generation hooks.
- Backgrounds and sprites.
- Backup/import/export.
- Extension or add-on compatibility where practical.

## Compatibility Tiers

### Tier 1 — Import Compatibility

Character cards, lorebooks, presets, backgrounds, sprites, and similar assets should be imported or converted into LoreHearth-native formats.

### Tier 2 — Behavior Compatibility

Common script and command workflows should be reproduced through a LoreHearth command engine where practical.

### Tier 3 — Runtime Compatibility

Complex JavaScript, browser, or server-dependent extensions may require an adapter layer. Unsupported APIs must fail with clear reasons.

## Mobile Complaint Fixes

LoreHearth should improve common pain points:

- No Termux requirement for normal use.
- Android-native file picker.
- Clear onboarding.
- Manual model ID field.
- Provider presets.
- Readable errors.
- No dead buttons.
- Better phone/tablet layout.
- Cloud mode first.
- Offline Campfire Mode later.
- Honest feature labels.
