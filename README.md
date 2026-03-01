# 🚀 Spring AI : From Java Developer to AI Engineer

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3+-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring AI](https://img.shields.io/badge/Spring%20AI-1.0.0--M1-blue.svg)](https://spring.io/projects/spring-ai)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Welcome to the official repository for the **Spring AI**. This course is designed to empower Java developers to build next-generation, intelligent applications by integrating Large Language Models (LLMs) like OpenAI into the Spring Boot ecosystem.

---

## 📖 Essential Documentation
Core references for mastering the Spring AI framework and AI model integration.

*   **[Spring AI Official Documentation](https://docs.spring.io/spring-ai/reference/)** – The primary resource for modules, configuration, and portable API abstractions.
*   **[OpenAI Platform Guide](https://platform.openai.com/docs/)** – Technical documentation for GPT-4, Embeddings, and API usage.

---

## 🛠️ AI Runtimes & Infrastructure
Tools required to run, orchestrate, and deploy AI models locally and in the cloud.

| Tool | Purpose | Link |
| :--- | :--- | :--- |
| **Ollama** | Local LLM runtime (Llama 3, Mistral, etc.) | [Visit Ollama](https://ollama.com/) |
| **AWS Bedrock** | Managed Foundation Models on AWS | [Visit Bedrock](https://aws.amazon.com/bedrock/) |
| **Docker Desktop** | Infrastructure for local AI runtimes | [Visit Docker](https://www.docker.com/) |
| **Docker Model Runner** | Official Docker tool for AI model management | [Learn More](https://www.docker.com/) |

---

## 🧠 Foundational Science & RAG Tools
The theoretical pillars and data storage solutions for Retrieval-Augmented Generation (RAG).

*   **[Attention Is All You Need](https://arxiv.org/abs/1706.03762)** – The seminal research paper introducing the Transformer architecture.
*   **[Qdrant Vector Database](https://qdrant.tech/)** – High-performance vector store for semantic search and RAG implementations.
*   **[Model Context Protocol (MCP)](https://modelcontextprotocol.io/)** – An open standard for connecting AI clients to data sources.
*   **[OpenAI Tokenizer Tool](https://platform.openai.com/tokenizer)** – Visualizer for understanding token usage and cost optimization.

---

## 📊 Enterprise Observability
Ensuring reliability and performance in AI-driven Spring applications.

Building production-ready AI requires deep visibility into request flows and model performance. This project utilizes the following stack:

*   **Instrumentation:** [Micrometer](https://micrometer.io/) – The standard metrics collection library for Java.
*   **Metrics & Dashboards:** [Prometheus](https://prometheus.io/) and [Grafana](https://grafana.com/).
*   **Distributed Tracing:** [OpenTelemetry](https://opentelemetry.io/) and [Jaeger](https://www.jaegertracing.io/) for monitoring latency and LLM request-response cycles.

---

## 🚀 Getting Started

1.  **Clone the Repository:**
    ```bash
    git clone (https://github.com/ashok0202/spring_ai)
    ```
2.  **Install Local LLMs:** Download and install [Ollama](https://ollama.com/) to run models locally.
3.  **Configure Environment:** Set your `OPENAI_API_KEY` or configure local endpoints in `application.yml`.
4.  **Run with Docker:** Use the provided `docker-compose.yml` to spin up Qdrant, Prometheus, and Grafana.

---

## 🤝 Stay Connected & Support

If you have questions or encounter issues while working through the course:

*   **Raise an Issue:** Use the [GitHub Issues](https://github.com/ashok0202/spring_ai/issues) tab.
*   **Contribution:** Pull requests are welcome for documentation improvements or bug fixes.
*   **Instructor Support:** (https://www.linkedin.com/in/ashok-gunji-512b62252/)

**Happy Codding!** 
