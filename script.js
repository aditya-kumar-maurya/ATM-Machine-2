const BASE_URL = "http://localhost:8080/api/atm";
let currentAccountId = null;

function showMessage(text, isError = false) {
  const msgDivs = document.querySelectorAll("#message");
  msgDivs.forEach(div => {
    div.textContent = text;
    div.style.color = isError ? "#ff4d4d" : "#4dff4d";
  });
}

async function login() {
  const accountId = document.getElementById("accountId").value;
  const pin = document.getElementById("pin").value;

  if (!accountId || !pin) {
    showMessage("Account ID aur PIN dono bharo", true);
    return;
  }

  try {
    const res = await fetch(`${BASE_URL}/${accountId}/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ pin: Number(pin) })
    });
    const text = await res.text();

    if (text.includes("successful")) {
      currentAccountId = accountId;
      document.getElementById("loggedAccId").textContent = accountId;
      document.getElementById("loginView").classList.add("hidden");
      document.getElementById("dashboardView").classList.remove("hidden");
      showMessage("Login successful!");
    } else {
      showMessage(text, true);
    }
  } catch (err) {
    showMessage("Server se connect nahi ho paya. Backend chal raha hai?", true);
    console.error(err);
  }
}

async function checkBalance() {
  try {
    const res = await fetch(`${BASE_URL}/${currentAccountId}/balance`);
    const text = await res.text();
    showMessage(text);
  } catch (err) {
    showMessage("Error fetching balance", true);
    console.error(err);
  }
}

async function withdraw() {
  const amount = document.getElementById("amount").value;
  if (!amount) {
    showMessage("Amount enter karo", true);
    return;
  }
  try {
    const res = await fetch(`${BASE_URL}/${currentAccountId}/withdraw`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ amount: Number(amount) })
    });
    const text = await res.text();
    showMessage(text);
  } catch (err) {
    showMessage("Error while withdrawing", true);
    console.error(err);
  }
}

async function deposit() {
  const amount = document.getElementById("amount").value;
  if (!amount) {
    showMessage("Amount enter karo", true);
    return;
  }
  try {
    const res = await fetch(`${BASE_URL}/${currentAccountId}/deposit`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ amount: Number(amount) })
    });
    const text = await res.text();
    showMessage(text);
  } catch (err) {
    showMessage("Error while depositing", true);
    console.error(err);
  }
}

function logout() {
  currentAccountId = null;
  document.getElementById("dashboardView").classList.add("hidden");
  document.getElementById("loginView").classList.remove("hidden");
  document.getElementById("amount").value = "";
}
