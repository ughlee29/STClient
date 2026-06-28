# Release Instructions

1. Recreate full Android project scaffolding if this file set is imported into a new repo.
2. Add `settings.gradle.kts` and module-level Gradle files as needed.
3. Put API keys in local secure storage. Never commit them.
4. Build a debug APK first, then run on a physical Android device.
5. Run chat, character import, settings, RAG, TTS, image generation, backup, and accessibility smoke tests.
6. Only sign release builds with a private keystore stored outside the repository.
