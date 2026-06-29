# LoreHearth Copilot Instructions

LoreHearth is an Android-native character roleplay client.

Final target: SillyTavern-style workflow parity plus Android-native improvements and future lightweight offline RP.

Do not treat placeholder classes as completed features.

For v0.1, focus only on:

Settings → Character Import → Active Character → Chat → Real Model Reply → Persistence.

Rules:

- App name must be LoreHearth.
- Application ID must be `com.lorehearth.app`.
- Kotlin root package must be `com.lorehearth.app`.
- No hardcoded cloud responses in the production path.
- No fake character imports.
- No dead buttons.
- No future feature work unless explicitly assigned.
- All errors should be readable to the user.
- Every feature needs an acceptance test.
- Prefer simple Android-native flows over clever abstractions.
- Use Android file picker for imports, not phone-wide scanning in v0.1.
- Campfire/offline mode is future work until real local model inference exists.

When modifying code, preserve the phase-gated build plan in `docs/PHASE_GATED_BUILD_PLAN.md`.
