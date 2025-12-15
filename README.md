# 🛡️ Multi-User Secure Chat Service (Group Project — 2 members)
**Module:** CS2SNS — Secure Network Services  
**Assessment Weighting:** 50%  
**Deadline:** 22 December 2025  

---

## 👥 Group Information
**Group Name:** AqsaYuvraj

**Team Members:**
- Aqsa Amjad | 230066670
- Yuvraj Kular | 210155154  

---

## 🎯 Project Objectives
Your group must complete the following **5 levels**, arranged in order of difficulty:
1. **Level 1:**  
   Develop a multi-user chat server and client using basic sockets (text-based interface).

2. **Level 2:**  
   Implement **SSL/TLS encryption** to ensure message confidentiality over the network.

3. **Level 3:**  
   Identify one additional network security threat and implement a mitigation control.

4. **Level 4:**  
   Demonstrate the application working across **two physical machines**, configuring firewall rules to allow only necessary traffic.

5. **Level 5:**  
   Capture and analyse network traffic using **Wireshark** to show unencrypted vs encrypted data.

---

## 🧱 Deliverables
A single ZIP file will be submitted via Blackboard, containing:

| Component | Description |
|------------|-------------|
| **Written Report (PDF)** | Document design and implementation across all levels. |
| **Source Code (Java)** | All necessary Java files. |
| **Video Demo (MP4)** | 5-minute walkthrough of all levels, demonstrating how the code meets requirements. |

---

## 🧠 Learning Outcomes
By completing this project, we will:
- Demonstrate understanding of **socket programming** and **secure communication protocols**.
- Apply **confidentiality, integrity, and authentication** concepts in a practical system.
- Gain experience in configuring **network firewalls** and **analysing traffic** with Wireshark.
- Collaborate effectively on a secure software project from design to testing.

---
# Commands / Usage

- First, cd to src folder (secure-chat-service).
- If using separate physical devices, replace ```localhost``` with the server's IP address.

## Level 1 Basic Chat

1. Compile the Level 1 source files:

      ```javac Level1_basic_chat/*.java```
  
2. Start the server:

      ```java Level1_basic_chat.ChatServer```

3. Start a client

      ```java Level1_basic_chat.ChatClient localhost```

## Level 2 Secure Chat

1. Compile the Level 2 source files:

      ```javac Level2_secure_chat/*.java```

2. Start the secure server:

   ```java Level2_secure_chat.SecureChatServer```

4. Start a secure client:

   ```java Level2_secure_chat.SecureChatClient localhost```

## Level 3 Mutual Secure Chat

1. Compile the Level 3 source files:

      ```javac Level3_mutual_secure_chat/*.java```

2. Start the mutual secure server:

      ```java Level3_mutual_secure_chat.MutualSecureChatServer```

3. Start a mutual secure client:

      ```java Level3_mutual_secure_chat.MutualSecureChatClient localhost```
