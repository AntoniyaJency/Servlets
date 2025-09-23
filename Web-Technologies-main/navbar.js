// navbar.js
class MyNavbar extends HTMLElement {
  connectedCallback() {
    this.innerHTML = `
      <nav class="navbar">
        <ul class="nav-list">
          <li><a href="index.html">Home</a></li>
          <li><a href="exercise2.html">About</a></li>
          <li><a href="exercise3a.html">Dashboard</a></li>
          <li><a href="exercise3b.html">FAQs</a></li>
          <li><a href="exercise3c.html">Login</a></li>
        </ul>
      </nav>
    `;
  }
}

customElements.define('my-navbar', MyNavbar);
