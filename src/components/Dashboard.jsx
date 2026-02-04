export default function Dashboard({ user }) {
  return (
    <div>
      <h2>Dashboard</h2>
      <p>User ID: {user?.userId}</p>
      <p>Role: {user?.role}</p>
    </div>
  );
}
