# LoreHearth

LoreHearth is an Android-native character roleplay client focused on SillyTavern-style workflow parity, clean mobile UX, cloud API chat first, and future lightweight offline Campfire Mode.

This repository is being rebuilt from the original STClient scaffold. The original scaffold is preserved on `archive/original-stclient-scaffold` as reference material. The active repair branch is `feature/v0.1-hearth-spine`.

## v0.1 Hearth Spine

The first real milestone is deliberately small:

1. Settings
2. Character import
3. Active character selection
4. Hearth chat
5. Real model reply through an OpenAI-compatible cloud route
6. Persistence

Anything else is future work until this path works on a real Android device.

## Final Target

LoreHearth aims for SillyTavern-compatible power, Android-native simplicity, and offline-ready roleplay.

That means compatibility with common user content and workflows where practical, not copying another project's source code.

## Rules

Before touching code, read:

- `docs/ANDROID_STUDIO_RULES.md`
- `docs/LOREHEARTH_COMPATIBILITY_CONTRACT.md`
- `docs/PHASE_GATED_BUILD_PLAN.md`
- `.ai/AI_CODER_MASTER_RULES.md`

A feature is complete only when the user can perform it in the app and the acceptance test passes. A placeholder class does not count.

## Current Branches

- `main`: original public default branch for now.
- `archive/original-stclient-scaffold`: original scaffold preserved.
- `dev`: integration branch.
- `feature/v0.1-hearth-spine`: active repair branch.
