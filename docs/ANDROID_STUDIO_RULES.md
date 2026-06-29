# LoreHearth — Android Studio Rules

## Project Identity

The project is LoreHearth.

Required identity:

- App name: `LoreHearth`
- Application ID: `com.lorehearth.app`
- Kotlin root package: `com.lorehearth.app`
- Old names such as `STClient`, `Tryst`, `TavernTales`, `com.yourapp.stclient`, and `com.sillytavern.kotlin` must not remain in the production app path.

## Android Studio Work Rules

1. Work from a Git branch, never directly on `main`.
2. Build one phase at a time.
3. Do not add future features while fixing the current phase.
4. Do not create placeholder buttons in the production path.
5. Do not create fake API responses and call them working.
6. Do not create fake imports and call them character import.
7. Do not silently swallow errors.
8. Every failure must show a readable user-facing error.
9. All settings that affect chat must be visible and editable.
10. All claimed features must have an acceptance test.

## Branching Rules

Recommended branches:

- `main` — stable accepted milestones only.
- `dev` — integration branch.
- `feature/v0.1-hearth-spine` — first working app branch.
- `archive/original-stclient-scaffold` — original scaffold reference only.

## v0.1 Production Path

The only required production path for v0.1 is:

1. App launches.
2. User opens Settings.
3. User enters base URL, API key, model ID, temperature, and max output tokens.
4. User imports a character manually.
5. User selects active character.
6. User sends message.
7. App makes real cloud API request.
8. App displays real model reply.
9. Settings, character, and chat persist after restart.

## Forbidden in v0.1 Production Path

The following must not be in the active production path for v0.1:

- Phone-wide character scanning.
- Fake local model replies.
- Fake cloud replies.
- RAG.
- TTS.
- Image generation.
- VN mode.
- Group chat.
- Extensions.
- Script compatibility.
- Themes beyond basic usability.

These are valid future features, but they cannot block v0.1.

## Definition of Done

A task is done only when:

- App builds.
- App launches.
- User flow works.
- Errors are readable.
- No fake placeholder is presented as real.
- Acceptance test passes.
