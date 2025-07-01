import React, { useEffect, useState } from "react";
import axios from "axios";

function App() {
    const [matches, setMatches] = useState([]);
    const [formData, setFormData] = useState({
        teamA: "",
        teamB: "",
        ticketPrice: "",
        availableSeats: ""
    });
    const [editingId, setEditingId] = useState(null); // Track match being edited

    const fetchMatches = async () => {
        const res = await axios.get("http://localhost:8080/matches");
        setMatches(res.data);
    };

    useEffect(() => {
        fetchMatches();
    }, []);

    const handleChange = (e) => {
        setFormData((prev) => ({
            ...prev,
            [e.target.name]: e.target.value
        }));
    };

    const handleSubmit = async () => {
        if (editingId !== null) {
            // Edit match
            await axios.put(`http://localhost:8080/matches/${editingId}`, formData);
            setEditingId(null);
        } else {
            // Add new match
            await axios.post("http://localhost:8080/matches", formData);
        }

        setFormData({ teamA: "", teamB: "", ticketPrice: "", availableSeats: "" });
        fetchMatches();
    };

    const handleDelete = async (id) => {
        await axios.delete(`http://localhost:8080/matches/${id}`);
        fetchMatches();
    };

    return (
        <div style={{ padding: "2rem", fontFamily: "Arial, sans-serif" }}>
            <h1>Match Manager</h1>

            <h2>{editingId !== null ? "Edit Match" : "Add Match"}</h2>
            <input
                placeholder="Team A"
                name="teamA"
                value={formData.teamA}
                onChange={handleChange}
            />
            <input
                placeholder="Team B"
                name="teamB"
                value={formData.teamB}
                onChange={handleChange}
            />
            <input
                placeholder="Ticket Price"
                name="ticketPrice"
                value={formData.ticketPrice}
                onChange={handleChange}
                type="number"
            />
            <input
                placeholder="Available Seats"
                name="availableSeats"
                value={formData.availableSeats}
                onChange={handleChange}
                type="number"
            />
            <button onClick={handleSubmit}>
                {editingId !== null ? "Save Changes" : "Add Match"}
            </button>

            <h2>Matches</h2>
            <ul>
                {matches.map((m) => (
                    <li key={m.id}>
                        {m.teamA} vs {m.teamB} - ${m.ticketPrice} ({m.availableSeats} seats)
                        <button onClick={() => handleDelete(m.id)} style={{ marginLeft: "1rem" }}>
                            Delete
                        </button>
                        <button
                            onClick={() => {
                                setFormData({
                                    teamA: m.teamA,
                                    teamB: m.teamB,
                                    ticketPrice: m.ticketPrice,
                                    availableSeats: m.availableSeats
                                });
                                setEditingId(m.id);
                            }}
                            style={{ marginLeft: "0.5rem" }}
                        >
                            Edit
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default App;
