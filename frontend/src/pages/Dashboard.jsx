// src/pages/Dashboard.jsx
import { useState } from 'react';
import AccountList from '../components/accounts/AccountList';
import { mockAccounts } from '../mocks/accounts';

function Dashboard() {
    const [accounts] = useState(mockAccounts);

    return (
        <div>
            <h1>Dashboard</h1>
            <AccountList accounts={accounts} />
        </div>
    );
}

export default Dashboard;