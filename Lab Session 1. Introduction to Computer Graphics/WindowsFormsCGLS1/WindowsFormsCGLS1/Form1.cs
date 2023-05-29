using System;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Windows.Forms;

namespace WindowsFormsCGLS1
{
    public partial class Form1 : Form
    {
        public int tx, ty, dx, dy, r, dr;
        public float s = 1;


        public Matrix myMatrix;
        public Point[] triangle = {
            new Point(0,0),
            new Point(100,100),
            new Point(0,200)
        };

        private void timer1_Tick(object sender, EventArgs e)
        {
            tx += dx;
            ty += dy;
            myMatrix.Translate(dx, dy, MatrixOrder.Append);
            myMatrix.Rotate(dr);
            myMatrix.Scale(s, s);
            s = 1;
            if (tx > Size.Width)
            {
                myMatrix.Translate(-tx, 0, MatrixOrder.Append);
                tx = 0;
            }
            if (tx < 0)
            {
                myMatrix.Translate(Size.Width, 0, MatrixOrder.Append);
                tx = Size.Width;
            }
            if (ty > Size.Height)
            {
                myMatrix.Translate(0, -ty, MatrixOrder.Append);
                ty=0;
            }
            if (ty < 0)
            {
                myMatrix.Translate(0, Size.Height, MatrixOrder.Append);
                ty = Size.Height;
            }
            this.Invalidate();
        }

        public Form1()
        {
            InitializeComponent();
            this.DoubleBuffered = true; // prevent glitching
            this.KeyPreview = true;
            myMatrix = new Matrix();
            tx = 0;
            ty = 0;
            timer1.Start();
            // x = this.Width;
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;
            Pen myDrawingPen = new Pen(Color.LimeGreen);
            Brush myDrawingBrush = new SolidBrush(Color.LimeGreen);

            // x =
            // g.DrawLine(myDrawingPen,0,0,x,Size.Height);
            g.Transform = myMatrix;
            System.Console.WriteLine(myMatrix.Elements.ToString());
            g.FillPolygon(myDrawingBrush, triangle);
        }

        private void Form1_KeyPress(object sender, KeyPressEventArgs e)
        {
            System.Console.WriteLine(e.KeyChar);
            foreach (float v in myMatrix.Elements) {
                System.Console.WriteLine(v.ToString());
            }
            if (e.KeyChar == 's')
            {
                dx = 0;
                dy = 0;
            }
            if (e.KeyChar == 'a')
            {
                dx = -1;
                dy = 0;
            }
            if (e.KeyChar == 'd') {
                dx = 1;
                dy = 0;
            }
            if (e.KeyChar == 'w')
            {
                dx = 0;
                dy = -1;
            }
            if (e.KeyChar == 'x')
            {
                dx = 0;
                dy = 1;
            }
            if (e.KeyChar == 'q') {
                dx = -1;
                dy = -1;
            }
            if (e.KeyChar == 'z')
            {
                dx = -1;
                dy = 1;
            }
            if (e.KeyChar == 'e')
            {
                dx = 1;
                dy = -1;
            }
            if (e.KeyChar == 'c') {
                dx = 1;
                dy = 1;
            }
            if (e.KeyChar == 'r')
            {
                if (dr == 0)
                {
                    dr = 1;
                }
                else {
                    dr *= 2;
                }
            }
            if (e.KeyChar == 'R')
            {
                if (dr==1) {
                    dr = 0;
                }
                else
                {
                    dr /= 2;
                }
            }
            if (e.KeyChar == 'f')
            {
                s = 2;
            }
            if (e.KeyChar == 'F')
            {
                s = 0.5F;
            }
        }
    }
}
