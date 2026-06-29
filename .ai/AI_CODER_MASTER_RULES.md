# LoreHearth — AI Coder Master Rules

These rules apply to Qwen, Gemini, Codex, Copilot, or any AI coding assistant.

## Prime Directive

Build LoreHearth phase by phase.

Do not add features outside the assigned phase.

## No Placeholder Claims

Forbidden claims:

- API implemented if the code returns a hardcoded string.
- Import implemented if the button parses empty JSON.
- Offline LLM implemented if no model is loaded.
- Settings implemented if fields do not save.
- Feature complete if no user-facing flow exists.

## Required Report After Every Coding Task

The AI coder must report:

1. Files changed.
2. Classes/functions changed.
3. What user action now works.
4. How to test it manually.
5. What remains unfinished.
6. Whether any placeholder remains.
7. Whether the app builds.
8. Whether the acceptance test passes.

## Scope Control

If assigned Phase 2 Settings, do not work on RAG, TTS, VN mode, image generation, group chat, extensions, offline LLM, or themes.

If assigned Phase 3 Cloud Chat, do not work on future features.

## Compatibility Goal

LoreHearth final target is SillyTavern-style workflow parity plus Android-native improvements.

This means compatibility with common user content and workflows, not copying another project's source code.

## v0.1 Target

Only this path matters for v0.1:

Settings → Character Import → Active Character → Chat → Real Model Reply → Persistence.

Everything else waits.
