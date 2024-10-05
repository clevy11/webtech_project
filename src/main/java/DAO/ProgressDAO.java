package DAO;

import MODEL.Progress;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgressDAO {
    private Connection connection;

    /**
     * Constructor to establish a database connection.
     */
    public ProgressDAO() {
        try {
            // 1. Load the MySQL JDBC driver explicitly
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");

            // 2. Establish the connection to the database
            // Ensure the JDBC URL, username, and password are correct
            // If XAMPP's MySQL is using a different port or credentials, update accordingly
            String jdbcURL = "jdbc:mysql://localhost:3306/afrilingo";
            String dbUser = "root";
            String dbPassword = "";

            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database. Check JDBC URL, username, and password.");
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    /**
     * Inserts a new Progress record into the database.
     *
     * @param progress The Progress object containing data to insert.
     */
    public void createProgress(Progress progress) {
        String sql = "INSERT INTO progress (user_id, module_id, progress_status, completion_date, score, attempts) VALUES (?, ?, ?, ?, ?, ?)";
        if (connection == null) {
            System.err.println("Database connection is not established.");
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, progress.getUserId());
            statement.setInt(2, progress.getModuleId());
            statement.setString(3, progress.getProgressStatus());
            statement.setString(4, progress.getCompletionDate());
            statement.setInt(5, progress.getScore());
            statement.setInt(6, progress.getAttempts());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new progress record was inserted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting progress record.");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all Progress records from the database.
     *
     * @return A list of Progress objects.
     */
    public List<Progress> getAllProgress() {
        List<Progress> progressList = new ArrayList<>();
        String sql = "SELECT * FROM progress";
        if (connection == null) {
            System.err.println("Database connection is not established.");
            return progressList;
        }
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Progress progress = new Progress(
                        resultSet.getInt("progress_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("module_id"),
                        resultSet.getString("progress_status"),
                        resultSet.getString("completion_date"),
                        resultSet.getInt("score"),
                        resultSet.getInt("attempts")
                );
                progressList.add(progress);
            }
            System.out.println("Retrieved all progress records successfully. Total records: " + progressList.size());
        } catch (SQLException e) {
            System.err.println("Error retrieving progress records.");
            e.printStackTrace();
        }
        return progressList;
    }



    /**
     * Retrieves a specific Progress record by its ID.
     *
     * @param id The ID of the Progress record.
     * @return The Progress object if found; otherwise, null.
     */
    public Progress getProgressById(int id) {
        Progress progress = null;
        String sql = "SELECT * FROM progress WHERE progress_id = ?";
        if (connection == null) {
            System.err.println("Database connection is not established.");
            return progress;
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    progress = new Progress(
                            resultSet.getInt("progress_id"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("module_id"),
                            resultSet.getString("progress_status"),
                            resultSet.getString("completion_date"),
                            resultSet.getInt("score"),
                            resultSet.getInt("attempts")
                    );
                    System.out.println("Retrieved progress record with ID: " + id);
                } else {
                    System.out.println("No progress record found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving progress record by ID.");
            e.printStackTrace();
        }
        return progress;
    }

    /**
     * Updates an existing Progress record in the database.
     *
     * @param progress The Progress object containing updated data.
     */
    public void updateProgress(Progress progress) {
        String sql = "UPDATE progress SET user_id = ?, module_id = ?, progress_status = ?, completion_date = ?, score = ?, attempts = ? WHERE progress_id = ?";
        if (connection == null) {
            System.err.println("Database connection is not established.");
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, progress.getUserId());
            statement.setInt(2, progress.getModuleId());
            statement.setString(3, progress.getProgressStatus());
            statement.setString(4, progress.getCompletionDate());
            statement.setInt(5, progress.getScore());
            statement.setInt(6, progress.getAttempts());
            statement.setInt(7, progress.getProgressId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Progress record updated successfully.");
            } else {
                System.out.println("No progress record found with ID: " + progress.getProgressId());
            }
        } catch (SQLException e) {
            System.err.println("Error updating progress record.");
            e.printStackTrace();
        }
    }

    /**
     * Deletes a Progress record from the database by its ID.
     *
     * @param id The ID of the Progress record to delete.
     */
    public void deleteProgress(int id) {
        String sql = "DELETE FROM progress WHERE progress_id = ?";
        if (connection == null) {
            System.err.println("Database connection is not established.");
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Progress record deleted successfully.");
            } else {
                System.out.println("No progress record found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting progress record.");
            e.printStackTrace();
        }
    }

    /**
     * Closes the database connection.
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed successfully.");
            } catch (SQLException e) {
                System.err.println("Error closing database connection.");
                e.printStackTrace();
            }
        }
    }
}
