package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Entities.Note;
import com.helper.FactoryProvider;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			String noteIdParameter = request.getParameter("note_id");
			
			if(noteIdParameter!= null && !noteIdParameter.isEmpty())
			{
				int noteId = Integer.parseInt(noteIdParameter);
				System.out.println("The Note Id is this:"+noteId);
				
				try
				{
						Session s= FactoryProvider.getFactory().openSession();
						Transaction tx =s.beginTransaction();
						
						Note note= s.load(Note.class, noteId);
						s.delete(note);
						tx.commit();
						s.close();
						response.sendRedirect("all_notes.jsp");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				response.sendError(HttpServletResponse.SC_NOT_FOUND,"Note not Found");
			}
			
			
	}

	
	

}
