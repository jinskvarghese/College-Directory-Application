window.onload = function() {
    // Get token and role from localStorage
    const token = localStorage.getItem('token');
    const role = localStorage.getItem('role');

    if (!token) {
        // Redirect to login page if the user is not authenticated
        window.location.href = 'index.html';
    }

    // Display the role-specific content
    document.getElementById('user-role').textContent = `Logged in as: ${role}`;
};

// Logout function
document.getElementById('logout-button').addEventListener('click', function() {
    localStorage.removeItem('token');  // Remove token
    localStorage.removeItem('role');   // Remove role
    window.location.href = 'index.html';  // Redirect to login page
});
