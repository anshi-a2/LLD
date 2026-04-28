# 🔗 URL Shortener System Design (LLD + HLD)

---

# 📌 1. Problem Statement

A URL shortener converts a long URL into a short, unique alias. When a user hits the short URL, they are redirected to the original long URL.

Example:

```
https://www.google.com → http://localhost:8080/g8
```

---

# 🧠 2. Requirement Clarification

## ✅ Functional Requirements

* Generate short URL from long URL
* Redirect short URL → original URL
* Handle multiple requests concurrently
* Track number of clicks

## ❌ Non-Functional Requirements

* Low latency (fast redirect)
* High availability
* Scalable
* Unique short codes

---

# 🏗️ 3. High-Level Design (HLD)

## Components:

1. Client (Browser / Curl)
2. Controller (API layer)
3. Service (Business logic)
4. Storage (In-memory map for now)

### Flow:

**Shorten URL Flow**

1. User sends long URL
2. Generate unique ID
3. Convert ID → Base62
4. Store mapping
5. Return short URL

**Redirect Flow**

1. User hits short URL
2. Lookup mapping
3. Increment click count
4. Redirect (HTTP 302)

---

# 🧩 4. Low-Level Design (LLD)

## Core Classes

### 1. UrlMapping

```
shortCode
longUrl
createdAt
clickCount
```

### 2. ShortenUrlService

* Generate ID
* Encode to Base62
* Store mapping
* Fetch original URL

### 3. Controller

* POST /shorten
* GET /{code}

---

# ⚙️ 5. API Design

## 1. Create Short URL

**POST /shorten**

Request:

```json
{
  "longUrl": "https://www.google.com"
}
```

Response:

```
http://localhost:8080/g8
```

---

## 2. Redirect

**GET /{code}**

Response:

* 302 Redirect → Original URL
* 404 if not found

---

# 🔑 6. Key Design Decisions

## 1. Unique ID Generation

* Using AtomicLong
* Thread-safe

## 2. Encoding Strategy

* Base62 encoding
* Produces short readable URLs

## 3. Storage

* ConcurrentHashMap
* Thread-safe in-memory store

---

# ⚡ 7. Code Hints

## ID Generation

```
AtomicLong counter = new AtomicLong(1000);
long id = counter.incrementAndGet();
```

## Base62 Encoding

```
while (id > 0) {
  sb.append(CHARSET.charAt(id % 62));
  id /= 62;
}
```

## Store Mapping

```
map.put(code, new UrlMapping(code, longUrl));
```

## Redirect

```
return ResponseEntity.status(302)
       .location(URI.create(originalUrl))
       .build();
```

---

# 🧪 8. Testing via Curl

## Shorten URL

```
curl -X POST http://localhost:8080/shorten \
-H "Content-Type: application/json" \
-d '{"longUrl":"https://www.google.com"}'
```

## Redirect

```
curl -v http://localhost:8080/g8
```

---

# 🚀 9. Scalability Improvements (Interview Gold)

## 1. Replace In-Memory Store

* Use Redis (fast lookup)
* Use DB (persistent storage)

## 2. Distributed ID Generation

* Snowflake ID
* Avoid single point bottleneck

## 3. Caching

* Cache hot URLs in Redis

## 4. Load Balancing

* Multiple instances behind load balancer

## 5. Analytics

* Track clicks using Kafka

---

# 🧠 10. Interview Summary

* Used HashMap + Base62
* Achieved O(1) lookup
* Ensured thread safety using AtomicLong + ConcurrentHashMap
* Designed clean layered architecture

---

# ✅ 11. Future Enhancements

* Custom alias support
* URL expiry (TTL)
* Rate limiting
* Authentication
* QR code generation

---

# 🎯 Final Note

This implementation is ideal for:

* LLD interviews
* Backend system design rounds
* Hands-on demo projects

---
