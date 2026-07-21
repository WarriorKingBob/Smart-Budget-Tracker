import {useState} from "react";

function TransactionForm({ onSubmit, initialValues }) {
    const [amount, setAmount] = useState(initialValues?.amount ?? '');
    const [type, setType] = useState(initialValues?.type ?? 'EXPENSE');
    const [category, setCategory] = useState(initialValues?.category ?? '');
    const [description, setDescription] = useState(initialValues?.description ?? '');
    const [date, setDate] = useState(initialValues?.date ?? '');

    function handleSubmit(e) {
        e.preventDefault();
        onSubmit({ amount: parseFloat(amount), type, category, description, date });
    }

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Type:
                <select value={type} onChange={(e) => setType(e.target.value)}>
                    <option value="INCOME">Income</option>
                    <option value="EXPENSE">Expense</option>
                </select>
            </label>

            <label>
                Amount:
                <input
                    type="number"
                    min="0.01"
                    step="0.01"
                    value={amount}
                    onChange={(e) => setAmount(e.target.value)}
                    required
                />
            </label>

            <label>
                Category:
                <select value={category} onChange={(e) => setCategory(e.target.value)}>
                    <option value="">Select category</option>
                    <option value="Groceries">Groceries</option>
                    <option value="Rent">Rent</option>
                    <option value="Salary">Salary</option>
                    <option value="Entertainment">Entertainment</option>
                </select>
            </label>

            <label>
                Description:
                <input
                    type="text"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                />
            </label>

            <label>
                Date:
                <input
                    type="date"
                    value={date}
                    onChange={(e) => setDate(e.target.value)}
                    required
                />
            </label>

            <button type="submit">Save</button>
        </form>
    );
}

export default TransactionForm;