# Start Hub (Selenium 4)
java -jar selenium-server-4.13.0.jar hub

# Start Nodes (auto-detect drivers)
java -jar selenium-server-4.13.0.jar node --detect-drivers

# Check registered nodes in browser
http://localhost:4444/ui/


# Selenium 3 Grid Practical Notes

Selenium Grid allows you to run tests on multiple machines, browsers, and operating systems **in parallel**. Selenium 3 uses a **Hub-Node architecture**.

---

## 1️⃣ Start Hub (Selenium 3)

```bash
# Start Hub
java -jar selenium-server-standalone-3.141.59.jar -role hub

# Optional: Specify port
java -jar selenium-server-standalone-3.141.59.jar -role hub -port 4444

# Start Node and register with Hub
java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register

# Start Node with specific browsers and max instances
java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register -browser browserName=chrome,maxInstances=2 -browser browserName=firefox,maxInstances=2

#Check Registered Nodes
http://localhost:4444/grid/console

