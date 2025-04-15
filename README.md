# 🔐 Cipher Application

A Java-based desktop application that allows users to **encrypt** and **decrypt** text files using custom transformation and substitution techniques.

📎 **Detailed report and screenshots available in the attached PDF**  
`Cipher_Application_Report.pdf`

---

## 📌 Overview

The **Cipher Application** demonstrates fundamental cryptography concepts by allowing users to securely transform text files. It supports both **encryption** and **decryption**, making it a simple and educational tool for learning basic data security mechanisms.

---

## ⚙️ Features

- Encrypts text files by:
  - Uppercasing letters
  - Reordering parts of the string
  - Character substitutions (e.g., `A` → `@`, `E` → `=`, etc.)
- Decrypts files using reverse logic
- Simple and interactive GUI for selecting files and destinations
- Built with Java and uses file streams for input/output

---

## 📋 Pseudocode (Simplified)

```java
function encrypt(input, output):
    read line
    if line length >= 4:
        clean and uppercase
        split and reorder
        substitute characters
        write encrypted line
    else:
        write as-is

function decrypt(input, output):
    read line
    reverse substitutions
    reorder characters back
    lowercase
    write decrypted line
```

---

## 🖼️ Screenshots (Preview in PDF)

### 🔐 Encryption Flow:
1. Select "Encrypt Files"
2. Choose input file
3. Select destination
4. Encrypted file saved

### 🔓 Decryption Flow:
1. Select "Decrypt Files"
2. Choose encrypted file
3. Select destination
4. Decrypted file saved

📷 **Full screenshots are available in the report PDF**

---

## 🎯 Objective

To understand the practical application of cryptography through building an encryption/decryption tool. This project gives hands-on experience in:

- Text transformation
- Secure data handling
- File manipulation in Java

---

## 📁 Files Included

- `Cipher.java` – Main logic for encryption and decryption
- `Cipher_Application_Report.pdf` – Full project report with flowchart, pseudocode, and screenshots
- `.jj`, `.jjt` – JavaCC grammar files used in the project (if applicable)

---

## 🚀 How to Run

1. Clone the repository
2. Open the project in any Java IDE (e.g., Eclipse, IntelliJ)
3. Run `Cipher.java`
4. Use the GUI to encrypt or decrypt files

---

## 🧠 Conclusion

This project provided a practical insight into basic cryptographic techniques. It serves as a great starting point for anyone interested in cybersecurity or secure software development.

> Feel free to contribute or suggest enhancements!
```