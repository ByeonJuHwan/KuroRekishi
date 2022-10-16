package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kurorekishimain.KuroRekishiMain;
import oracle.jdbc.OracleDriver;
import static ojdbc.MysqlJdbc.*; // 맥북용 Mysql jdbc
import static member.Member.Entity.*;
//import static ojdbc.OracleJdbc.*; // oracle jdbc
import static member.JdbcSql.*;


public class MemberDaoImpl implements MemberDao{
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	// 데이터베이스 접속 메서드 -- for MAC
	private void connDB() {
		try {
		    Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 데이터베이스 접속 메서드 -- for Windows
//	private void connDB() {
//        try {
//            DriverManager.registerDriver(new OracleDriver());
//            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
	
	
	// 연결 close() 시 사용하는 메서드 -- executeUpdate() 인경우
	private void closeResources(Connection conn, Statement stmt) throws SQLException{
        stmt.close();
        conn.close();
    }
	
	// 연결 close() 시 사용하는 메서드 -- executeQuery() 인경우 / 위 메서드를 오버로드 해준다.
	private void closeResources(Connection conn, Statement stmt, ResultSet rs) throws SQLException{
        rs.close();
        closeResources(conn,stmt);
    }

	// singleton
    // 1. 자기자신 타입의 private static인 변수 선언
    private static MemberDaoImpl instance = null;
    
    // 2. private 생성자
    private MemberDaoImpl () {}
    // 3. 자기 자신을 리턴하는 public static 메서드 정의
    public static MemberDaoImpl getInstance() {
        if(instance == null) {
            instance  = new MemberDaoImpl();
        }
        return instance;
    }
    
    
    /**
     * 중복되는 아이디 검열
     * 
     */
    @Override
	public boolean findIdExist(String id) {
		boolean result = false;
		try {
			connDB();
			
			stmt = conn.prepareStatement(SQL_ISEXIST_FOR_MAC);
			
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			rs.next();
			
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result : " + result);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

    /**
     * 회원 가입
     */
	@Override
	public int addmember(Member member) {
		int result = 0;
		try {
			connDB();
			
			stmt = conn.prepareStatement(SQL_INSERT_MEMBER);
			
			stmt.setString(1, member.getId());
			stmt.setString(2, member.getPw());
			stmt.setString(3, member.getName());
			stmt.setString(4, member.getSex());
			stmt.setString(5, member.getLocation());
			stmt.setString(6, member.getHistory());
			stmt.setString(7, member.getHight());
			stmt.setString(8, member.getAge());
			stmt.setString(9, member.getMbti());
			
			result  = stmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				closeResources(conn, stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 회원수정
	 * @return 
	 */
	@Override
	public int updateMember(Member member) {
        int result = 0;
	    try {
	        connDB();
	        
	        stmt = conn.prepareStatement(SQL_UPDATE_MEMBER);
	        stmt.setString(1, member.getId());
	        stmt.setString(2, member.getPw());
	        stmt.setString(3, member.getName());
	        stmt.setString(4, member.getLocation());
	        stmt.setString(5, member.getSex());
	        stmt.setString(6, member.getHistory());
	        stmt.setString(7, member.getHight());
	        stmt.setString(8, member.getAge());
	        stmt.setString(9, member.getMbti());
	        stmt.setString(10, member.getId());
	        
	        result = stmt.executeUpdate();
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally {
	        try {
                closeResources(conn, stmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }
	    return result;
	}
	
	/**
	 * 아이디를 주고 이름을 받는 메서드(채팅창, 로그인)
	 */
	@Override
	public String fineName(String id) {
		String name = null;
		try {
			connDB();
			
			stmt = conn.prepareStatement(SQL_MEMBER_NAME_BY_ID);
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			rs.next();
			name = rs.getString(COL_MEM_NAME);
			
			
			System.out.println("회원이름 = " + name);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return name;
	}
	
	/**
	 * 로그인
	 */
    @Override
    public boolean Login(String id, String pw) {
        boolean result = false;
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_LOGIN_FORMAC); 
            stmt.setString(1, id);               
            stmt.setString(2, pw);               
            rs = stmt.executeQuery(); 
            rs.next(); 
            result = Boolean.parseBoolean(rs.getString("result"));
            System.out.println("result=" + result);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
            	closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
		return result;
    }

    
    /**
     *  회원 정보를 id로 검색해서 리턴
     */
    @Override
    public List<Member> selectById(String id) {
        List<Member> list = new  ArrayList<Member>();
        try {
            connDB();
            
            stmt = conn.prepareStatement(SQL_SELECT_MEMBER_BY_ID);
            
            stmt.setString(1, id);
            
            rs = stmt.executeQuery();
            while(rs.next()) {
                String userid = rs.getString(COL_MEM_ID);
                String pw = rs.getString(COL_MEM_PW);
                String name = rs.getString(COL_MEM_NAME);
                String sex = rs.getString(COL_MEM_SEX);
                String loc = rs.getString(COL_MEM_LOC);
                String history = rs.getString(COL_MEM_HISTORY);
                String hight = rs.getString(COL_MEM_HIGHT);
                String age = rs.getString(COL_MEM_AGE);
                String mbti = rs.getString(COL_MEM_MBTI);
                
                Member member = new Member(userid,pw,name,sex,loc,history,hight,age,mbti);
                list.add(member);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) { 
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public String pickUserRamdom(String sex) {
        String name = null;
        try {
            connDB();
            
            stmt = conn.prepareStatement(SQL_SELECT_NAME_RANDOM_FOR_MAC);
            stmt.setString(1, sex);
            
            rs = stmt.executeQuery();
            if(rs.next()) {
            	name = rs.getString(COL_MEM_NAME);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
				closeResources(conn, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        return name;
    }

	@Override
	public String loginedUserSex(String id) {
		String sex = null;
		try {
			connDB();
			
			stmt = conn.prepareStatement(SQL_SELECT_SEX_BY_ID);
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				sex = rs.getString(COL_MEM_SEX);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sex;
	}

	@Override
	public int giveThumb(String giveName,String gavedname) {
		int result = 0;
		try {
			connDB();
			stmt = conn.prepareStatement(SQL_UPDATE_GIVETHUMB);
			stmt.setString(1, giveName);
			stmt.setString(2, gavedname);
			stmt.setString(3, gavedname);
			
			result = stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeResources(conn, stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public Member checkThumb(String id) {
		Member member = null;
		try {
			connDB();
			String query = "select NAME,GAVEDTHUMB,GIVETHUMB FROM MEMBER";
			query += " where ID=?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString(COL_MEM_NAME);
				String gavedName = rs.getString(COL_MEM_GAVEDTHUMBNAME);
				String giveName = rs.getString(COL_MEM_GIVETHUMBNAME);
				
				member = new Member(name,gavedName,giveName);
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

    @Override
    public String findLocById(String id) {
        String loc=null;
        try {
            connDB();
            
            stmt = conn.prepareStatement(SQL_SELECT_LOC);
            stmt.setString(1, id);
            
            rs = stmt.executeQuery();
            while(rs.next()) {
                loc = rs.getString(COL_MEM_LOC);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        return loc;
    }


    @Override
    public List<String> findHightOption(String lowHight, String maxHight) {
        String name = null;
        List<String>searchOptoinNameList = new ArrayList<>();
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_SELECT_HIGHT_SET);
            stmt.setString(1, lowHight);
            stmt.setString(2, maxHight);
            rs = stmt.executeQuery();
            while(rs.next()) {
                name = rs.getString(COL_MEM_NAME);
                searchOptoinNameList.add(name);
            }
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return searchOptoinNameList;
    }


    @Override
    public void setSearch(String id) {
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_UPDATE_SEARCHSET);
            stmt.setString(1, id);
            stmt.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeResources(conn, stmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }


    @Override
    public List<String> findAgeOption(String lowAge, String maxAge) {
        String name = null;
        List<String>searchOptoinNameList = new ArrayList<>();
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_SELECT_AGE_SET);
            stmt.setString(1, lowAge);
            stmt.setString(2, maxAge);
            rs = stmt.executeQuery();
            while(rs.next()) {
                name = rs.getString(COL_MEM_NAME);
                searchOptoinNameList.add(name);
            }
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return searchOptoinNameList;
    }


    
}
