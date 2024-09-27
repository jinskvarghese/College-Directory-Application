document.addEventListener('DOMContentLoaded', async () => {
    const token = localStorage.getItem('token');

    if (!token) {
        window.location.href = '/index.html';  // Redirect to login if not authenticated
        return;
    }

    // Fetch student profile
    try {
        const profileResponse = await fetch('http://localhost:8080/api/student/profile', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        const profileData = await profileResponse.json();
        document.getElementById('student-profile').innerHTML = `
            <h3>Your Profile</h3>
            <p>Name: ${profileData.name}</p>
            <p>ID: ${profileData.id}</p>
            <p>Department: ${profileData.department}</p>
            <p>Year: ${profileData.year}</p>
        `;

        // Fetch faculty advisors
        const advisorsResponse = await fetch('http://localhost:8080/api/student/advisors', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        const advisorsData = await advisorsResponse.json();
        const advisorsList = advisorsData.map(advisor => `<li>${advisor.name} (${advisor.email})</li>`).join('');
        document.getElementById('faculty-advisors').innerHTML = `
            <ul>${advisorsList}</ul>
        `;
    } catch (error) {
        console.error('Error fetching data:', error);
    }
});

// Function to search students
async function searchStudents() {
    const searchQuery = document.getElementById('student-search').value;
    const token = localStorage.getItem('token');

    if (searchQuery.length === 0) {
        document.getElementById('student-search-results').innerHTML = '';
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/student/search?query=${searchQuery}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        const studentsData = await response.json();
        const studentsList = studentsData.map(student => `
            <li>${student.name} (ID: ${student.id}) - ${student.department}</li>
        `).join('');

        document.getElementById('student-search-results').innerHTML = `
            <ul>${studentsList}</ul>
        `;
    } catch (error) {
        console.error('Error fetching students:', error);
    }
}
