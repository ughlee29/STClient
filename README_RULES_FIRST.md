# LoreHearth Rules Pack — Read First

These files are meant to live inside the LoreHearth GitHub repository before Android Studio work continues.

Android Studio itself will not automatically obey Markdown rules. The purpose of these files is to make the repo the source of truth for:

- Android Studio work.
- Gemini/Qwen/Codex/Copilot prompts.
- Pull request reviews.
- Feature planning.
- Acceptance tests.

## Recommended Placement

Copy this pack into the root of the LoreHearth repository.

Important files:

- `docs/ANDROID_STUDIO_RULES.md`
- `docs/LOREHEARTH_COMPATIBILITY_CONTRACT.md`
- `docs/PHASE_GATED_BUILD_PLAN.md`
- `.ai/AI_CODER_MASTER_RULES.md`
- `.github/copilot-instructions.md`

## Required Rule

No AI coder or developer may claim a feature is complete because a file/class/screen exists.

A feature is complete only when:

1. The user can perform the action in the app.
2. It uses real data, not placeholder data.
3. It survives the required persistence test if applicable.
4. It passes the acceptance test listed in the phase plan.
5. Any remaining placeholder behavior is clearly labeled as future work.

## First Build Target

v0.1 is only:

Settings → Character Import → Active Character → Chat → Real Model Reply → Persistence.

Everything else waits.
