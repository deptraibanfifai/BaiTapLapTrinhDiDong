package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.sothuchi.flightsearch.data.Airport

@Dao
interface FlightDao {
    // Tìm kiếm sân bay theo mã hoặc tên, sắp xếp theo lượng khách giảm dần
    @Query("""
        SELECT * FROM airport 
        WHERE iata_code LIKE :searchQuery OR name LIKE :searchQuery 
        ORDER BY passengers DESC
    """)
    fun getAirportsBySearch(searchQuery: String): Flow<List<Airport>>

    // Lấy thông tin 1 sân bay cụ thể dựa vào mã IATA
    @Query("SELECT * FROM airport WHERE iata_code = :iataCode LIMIT 1")
    fun getAirportByCode(iataCode: String): Flow<Airport?>

    // Lấy tất cả các sân bay khác (để làm điểm đến) trừ sân bay xuất phát
    @Query("SELECT * FROM airport WHERE iata_code != :departureCode ORDER BY passengers DESC")
    fun getAllDestinations(departureCode: String): Flow<List<Airport>>

    // Lấy toàn bộ danh sách yêu thích
    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    // Kiểm tra xem tuyến bay này đã được thích chưa
    @Query("SELECT EXISTS(SELECT 1 FROM favorite WHERE departure_code = :dep AND destination_code = :dest)")
    fun isFavorite(dep: String, dest: String): Flow<Boolean>
}