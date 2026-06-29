# LoreHearth — Phase-Gated Build Plan

## Rule

Do not move to the next phase until the current phase passes.

## Phase 0 — Repo Hygiene

Goal: Make the repo safe to work in.

Tasks:

- Preserve the original scaffold in `archive/original-stclient-scaffold`.
- Add rules docs.
- Add acceptance tests.
- Confirm Android Studio opens the project.

Acceptance test:

- Repo has rules docs.
- Old scaffold is preserved.
- Work branch exists.
- Android Studio can open project.

## Phase 1 — Identity and Launch

Goal: App builds and launches as LoreHearth.

Tasks:

- Package root: `com.lorehearth.app`.
- Application ID: `com.lorehearth.app`.
- App label: `LoreHearth`.
- MainActivity launches.
- Basic navigation exists.

Acceptance test:

- Debug APK builds.
- App installs.
- App launches.
- App name shows LoreHearth.
- No package mismatch.

## Phase 2 — Settings

Goal: User can configure cloud model access.

Tasks:

- Base URL field.
- API key field.
- Model ID field.
- Temperature field.
- Max output tokens field.
- Save settings.
- Load settings after restart.

Acceptance test:

- Settings save.
- App restarts.
- Settings remain.

## Phase 3 — Real Cloud Chat

Goal: One real model reply.

Tasks:

- Implement OpenAI-compatible `/chat/completions`.
- Use base URL/API key/model from settings.
- Send user message.
- Show loading state.
- Display assistant response.
- Display readable errors.

Acceptance test:

- Valid settings produce real model reply.
- Invalid key gives readable error.
- Missing model blocks request with readable error.

## Phase 4 — Character Import

Goal: Import a real character card.

Tasks:

- Android file picker.
- JSON card import.
- PNG card `chara` metadata import.
- Store imported character.
- Select active character.

Acceptance test:

- Import JSON character.
- Import PNG character.
- Character appears in Lore Vault.
- Active character is used in chat prompt.

## Phase 5 — Persistence

Goal: App remembers user data.

Tasks:

- Persist settings.
- Persist characters.
- Persist active character.
- Persist current chat.

Acceptance test:

- Configure settings.
- Import character.
- Chat.
- Force-close app.
- Reopen.
- Data remains.

## Phase 6 — RP Prompt Quality

Goal: Chat behaves like character RP, not generic assistant mode.

Tasks:

- Include character fields in prompt.
- Include scenario.
- Include recent history.
- Add simple RP instruction.
- Add prompt budget trimming.

Acceptance test:

- Selected character influences response.
- Character card remains in prompt after multiple messages.
- Prompt stays inside configured budget.

## Phase 7 — Campfire Mode Foundation

Goal: Prepare offline mode honestly.

Tasks:

- Add Campfire Mode screen.
- Add GGUF import path later.
- Research llama.cpp Android integration.
- Do not pretend local generation exists until a real engine exists.

Acceptance test:

- Offline mode says not implemented until real engine exists.
- No placeholder response is presented as model output.

## Phase 8+ — Advanced Parity

After v0.1 works, add:

- Regeneration/branching.
- Lorebook/World Info.
- Regex.
- Group chat.
- RAG.
- TTS.
- Translation.
- VN/sprites/backgrounds.
- Image generation hooks.
- Backup/export/import.
- Extension compatibility.
