document.getElementById('login-form').addEventListener('submit', async (event) => {
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value;
    
    const loginData = { username, password, role };

    try {
        const response = await fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        });

        if (!response.ok) {
            throw new Error('Login failed');
        }

        const data = await response.json();
        localStorage.setItem('token', data.token);
        localStorage.setItem('role', role);
        
        // Redirect to different pages based on role
        if (role === 'student') {
            window.location.href = '/student-dashboard.html';
        } else if (role === 'faculty') {
            window.location.href = '/faculty-dashboard.html';
        } else {
            window.location.href = '/admin-dashboard.html';
        }

    } catch (error) {
        document.getElementById('error-message').innerText = error.message;
    }
});
