# RAG Notes

This trimmed package keeps only the requested source files. The RAG path is:

1. Load documents or lore chunks.
2. Create embeddings with `EmbeddingProvider`.
3. Store vectors in `VectorDatabase`.
4. Retrieve top matches in `RAGPipeline`.
5. Inject retrieved context through `STPromptBuilder`.

Do not store API keys in source files. Use `ApiKeyManager` or Android encrypted storage in the full project.
